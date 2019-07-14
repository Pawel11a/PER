package service;

import convert.Mapper;
import dto.*;
import exceptions.MyException;
import lombok.RequiredArgsConstructor;
import model.*;
import model.Errors;
import org.apache.commons.lang3.StringUtils;
import repositories.impl.*;
import utils.UtilsMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class StockService {
    private static Logger LOGGER = Logger.getLogger("StockService");

    private final CategoryRepositoryImpl categoryRepository;
    private final ShopRepositoryImpl shopRepository;
    private final CountryRepositoryImpl countryRepository;
    private final StockRepositoryImpl stockRepository;
    private final ProductRepositoryImpl productRepository;

    public void addStock() {
    /*
    Pobierane są w postaci napisów nazwa produktu wraz z nazwą kategorii, nazwa sklepu wraz z krajem pochodzenia oraz ilość sztuk (ta dana może być pobierana jako liczba). W przypadku większej ilości produktów lub sklepów o podanych właściwościach należy zezwolić użytkownikowi na wybranie jednego z nich.
     */
        LOGGER.info("Start operation addStock()");

        try {
            StockDto stockDto = new StockDto();
            ShopDto shopDto = new ShopDto();
            CountryDto countryDto = new CountryDto();

            ProductDto productDto = new ProductDto();
            CategoryDto categoryDto = new CategoryDto();
            CountryDto countryDto1 = new CountryDto();
            categoryDto.setName("FOOD");
            productDto.setName("BATTERY");
            countryDto1.setName("ITALIA");
            productDto.setCategoryDto(categoryDto);

            ShopDto shopDto1 = new ShopDto();
            shopDto1.setName("ELO");
            shopDto1.setCountryDto(countryDto1);

            stockDto.setQuantity(2);
            stockDto.setProductDto(productDto);
            stockDto.setShopDto(shopDto1);

//            enterStockData(stockDto);

            validateStock(stockDto);

            Stock findRowbyNameProductAndShop = stockRepository.findByNameProductAndShop(stockDto.getProductDto().getName(), stockDto.getShopDto().getName());


            if (findRowbyNameProductAndShop != null) {
                findRowbyNameProductAndShop.setQuantity(findRowbyNameProductAndShop.getQuantity() + stockDto.getQuantity());
                stockRepository.saveOrUpdate(findRowbyNameProductAndShop);
            } else {
                Stock stock = Mapper.fromStockDtoToStock(stockDto);
                stockRepository.saveOrUpdate(stock);
            }
            LOGGER.info("End operation addStock()");
        } catch (Exception ex) {
            LOGGER.warning("Incorrect stock data " + ex);
            Errors stockError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.STOCK.name() + " - add stock");
        }

    }

    private void validateStock(StockDto stockDto) {

        List<Errors> lists = new ArrayList<>();

        Errors stockError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.STOCK.name() + " - add stock");
        if (stockDto == null) {
            Errors stockEmpty = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.STOCK.name() + " - stock object empty");
            lists.add(stockEmpty);
            ErrorService.saveError(stockEmpty);
            throw new MyException("Stock data are incorrect", lists);
        }

        if (stockDto.getProductDto() == null || stockDto.getShopDto() == null || stockDto.getQuantity() == null || stockDto.getShopDto().getCountryDto() == null || stockDto.getQuantity() == null) {

            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.STOCK.name() + " - stock datas is null"));
        } else if (StringUtils.isEmpty(stockDto.getProductDto().getName()) || StringUtils.isEmpty(stockDto.getProductDto().getCategoryDto().getName())
                || StringUtils.isEmpty(stockDto.getShopDto().getName()) || StringUtils.isEmpty(stockDto.getShopDto().getCountryDto().getName())
                || stockDto.getQuantity() < 0) {

            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.STOCK.name() + " - stock datas is empty"));
        } else {


            Country byNameCountry = countryRepository.findByNameCountry(stockDto.getShopDto().getCountryDto().getName());
            Category categorybyName = categoryRepository.findByName(stockDto.getProductDto().getCategoryDto().getName());
            Shop shopByName = shopRepository.findShopByName(stockDto.getShopDto().getName());
            Product productByName = productRepository.findProductByName(stockDto.getProductDto().getName());

//            stockDto.setShopDto(Mapper.fromShopToShopDto(shopByName));
//            stockDto.setProductDto(Mapper.fromProductToProductDto(productByName));
//            stockDto.setProductDto();
//            Mapper.fromShopToShopDto(shopByName);

            if (byNameCountry == null) {
                lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.STOCK.name() + " - stock countrys name isn't exists"));
            }

            if (categorybyName == null) {
                lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.STOCK.name() + " - stock categorys name isn't exists"));
            }

            if (shopByName == null) {
                lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.STOCK.name() + " - stock shops name isn't exists"));
            }

            if (productByName == null) {
                lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.STOCK.name() + " - stock products name isn't exists"));
            }


        }

        if (!lists.isEmpty()) {
            ErrorService.saveError(stockError);

            lists.stream().forEach(item -> LOGGER.warning(item.getDate() + " " + item.getMessage()));
            throw new MyException("Add stock data are incorrect", lists);
        }
    }

    private void enterStockData(StockDto stockDto) {
        Scanner sc = new Scanner(System.in);
        ProductDto productDto = new ProductDto();
        CategoryDto categoryDto = new CategoryDto();
        ShopDto shopDto = new ShopDto();
        CountryDto countryDto = new CountryDto();
//        productDto.setCategoryDto();
/*
private BigInteger id;
    private Integer quantity;
    private ProductDto productDto;
    private ShopDto shopDto;
 */
        System.out.println("enter the product name: ");
        productDto.setName(sc.nextLine());

        System.out.println("enter the category name: ");
        categoryDto.setName(sc.nextLine());
        productDto.setCategoryDto(categoryDto);

        System.out.println("enter the store name: ");
        shopDto.setName(sc.nextLine());

        System.out.println("enter the name of the country of origin: ");
        countryDto.setName(sc.nextLine());
        shopDto.setCountryDto(countryDto);

        System.out.println("enter the number of stocks: ");
        stockDto.setQuantity(sc.nextInt());
        sc.nextLine();

        stockDto.setShopDto(shopDto);
        stockDto.setProductDto(productDto);
    }


}

