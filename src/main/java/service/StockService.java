package service;

import convert.Mapper;
import dto.CountryDto;
import dto.ErrorsEnumDto;
import dto.ShopDto;
import dto.StockDto;
import exceptions.MyException;
import lombok.RequiredArgsConstructor;
import model.Country;
import model.Errors;
import org.apache.commons.lang3.StringUtils;
import repositories.impl.CountryRepositoryImpl;
import repositories.impl.ShopRepositoryImpl;
import repositories.impl.StockRepositoryImpl;
import utils.UtilsMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class StockService {
    private static Logger LOGGER = Logger.getLogger("StockService");

//    private final ShopRepositoryImpl shopRepository;
//    private final CountryRepositoryImpl countryRepository;
    private final StockRepositoryImpl stockRepository;

    public void addStock() {
    /*
    Pobierane są w postaci napisów nazwa produktu wraz z nazwą kategorii, nazwa sklepu wraz z krajem pochodzenia oraz ilość sztuk (ta dana może być pobierana jako liczba). W przypadku większej ilości produktów lub sklepów o podanych właściwościach należy zezwolić użytkownikowi na wybranie jednego z nich.
     */
        LOGGER.info("Start operation addStock()");

        try {
            ShopDto shopDto = new ShopDto();
            CountryDto countryDto = new CountryDto();

            enterStockData(shopDto, countryDto);

            validateStock(shopDto, countryDto);

            Country findCountryByName = stockRepository.findByNameCountry(countryDto.getName());

//TODO complete the operation
            if (findCountryByName != null) {
                shopDto.setCountryDto(Mapper.fromCountryToCountryDto(findCountryByName));

            } else {

                shopDto.setCountryDto(countryDto);
            }
            stockRepository.saveOrUpdate(Mapper.fromShopDtoToShop(shopDto));

            LOGGER.info("End operation addStock()");
        }catch(Exception ex){
            LOGGER.warning("Incorrect stock data " + ex);
            Errors stockError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.STOCK.name() + " - add stock");
        }

    }

    private void validateStock(StockDto stockDto, CountryDto countryDto) {

        List<Errors> lists = new ArrayList<>();

        Errors stockError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.STOCK.name() + " - add stock");
        if (stockDto == null) {
            Errors shopEmpty = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.STOCK.name() + " - stock object empty");
            lists.add(shopEmpty);
            ErrorService.saveError(shopEmpty);
            throw new MyException("Shop data are incorrect", lists);
        }

        if (StringUtils.isEmpty(stockDto.getName()) || !ValidateService.nameIsCorrect(stockDto.getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.SHOP.name() + " - stock name is incorrect"));
        }

        if (StringUtils.isEmpty(countryDto.getName()) || !ValidateService.nameIsCorrect(countryDto.getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.SHOP.name() + " - stock name is incorrect"));
        }

        if (!lists.isEmpty()) {
            ErrorService.saveError(stockError);

            lists.stream().forEach(item -> LOGGER.warning(item.getDate() + " " + item.getMessage()));
            throw new MyException("Add stock data are incorrect", lists);
        }
    }

    private void enterStockData(ShopDto shopDto, CountryDto countryDto) {
        Scanner sc = new Scanner(System.in);
/*
private BigInteger id;
    private Integer quantity;
    private ProductDto productDto;
    private ShopDto shopDto;
 */
        System.out.println("Enter the name of the produck: ");
        stockDto.setName(sc.nextLine());

        System.out.println("Enter the name of the category: ");
        stockDto.setName(sc.nextLine());

        System.out.println("Enter the name of the country: ");
        countryDto.setName(sc.nextLine());
    }


}

