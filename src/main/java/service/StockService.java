package service;

import dto.CountryDto;
import dto.ErrorsEnumDto;
import dto.ProductDto;
import dto.ShopDto;
import dto.StockDto;
import exceptions.MyException;
import lombok.RequiredArgsConstructor;
import model.Errors;
import model.Stock;
import org.apache.commons.lang3.StringUtils;
import repositories.impl.ProductRepositoryImpl;
import repositories.impl.ShopRepositoryImpl;
import utils.UtilsMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class StockService {

    private static Logger LOGGER = Logger.getLogger("StockService");
    /*
     stock – tabela przechowująca informacje o produktach. Kolumna quantity
reprezentuje ilość sztuk produktu w magazynie i musi być liczbą
nieujemną. W tabeli nie może znaleźć się dwa razy taki sam produkt
pochodzący z tego samego sklepu. W przypadku próby dodania takiego
produktu należy tylko powiększyć ilość sztuk produktu w istniejącej
już pozycji w magazynie.
     */
    private final ProductRepositoryImpl productRepository;
    private final ShopRepositoryImpl shopRepository;

    public void addStock() {

        StockDto stockDto = new StockDto();
        enterShopData(stockDto);

    }

    private void enterShopData(StockDto stockDto/*, ShopDto shopDto, CountryDto countryDto*/) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the quantity: ");
        stockDto.setQuantity(sc.nextInt());
        sc.nextLine();

        System.out.println("Enter the product name: ");
//        stockDto.setProductName(sc.nextLine()));
    }

    private void validateShop(ShopDto shopDto, CountryDto countryDto) {

        List<Errors> lists = new ArrayList<>();

        Errors shopError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.SHOP.name() + " - add shop");
        if (shopDto == null) {
            Errors shopEmpty = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.SHOP.name() + " - shop object empty");
            lists.add(shopEmpty);
            ErrorService.saveError(shopEmpty);
            throw new MyException("Shop data are incorrect", lists);
        }

        if (StringUtils.isEmpty(shopDto.getName()) || !ValidateService.nameIsCorrect(shopDto.getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.SHOP.name() + " - shop name is incorrect"));
        }

        if (StringUtils.isEmpty(countryDto.getName()) || !ValidateService.nameIsCorrect(countryDto.getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.SHOP.name() + " - shop name is incorrect"));
        }

        if (!lists.isEmpty()) {
            ErrorService.saveError(shopError);

            lists.stream().forEach(item -> LOGGER.warning(item.getDate() + " " + item.getMessage()));
            throw new MyException("Add shop data are incorrect", lists);
        }
    }
}
