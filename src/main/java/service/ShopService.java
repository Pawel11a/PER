package service;

import dto.CountryDto;
import dto.CustomerDto;
import dto.ErrorsEnumDto;
import dto.ShopDto;
import exceptions.MyException;
import lombok.RequiredArgsConstructor;
import model.Country;
import model.Errors;
import org.apache.commons.lang3.StringUtils;
import repositories.impl.CountryRepositoryImpl;
import repositories.impl.CustomerRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class ShopService {

    private static Logger LOGGER = Logger.getLogger("ShopService");

    private final CustomerRepositoryImpl customerRepository;
    private final CountryRepositoryImpl countryRepository;

    public void addShop() {

        LOGGER.info("Start operation addShop()");

        ShopDto shopDto = new ShopDto();
        CountryDto countryDto = new CountryDto();

        enterShopData(shopDto, countryDto);

        validateShop(shopDto, countryDto);

        Country findCountryByName = countryRepository.findByNameCountry(countryDto.getName());

//TODO complete the operation
        if(findCountryByName != null){



            LOGGER.info("End operation addShop()");
        }



        LOGGER.info("End operation addShop()");

    }

    private void validateShop(ShopDto shopDto, CountryDto countryDto) {

        List<Errors> lists = new ArrayList<>();

        Errors shopError = new Errors(ErrorsEnumDto.SHOP.name(), "add shop");
        if (shopDto == null) {
            Errors shopEmpty = new Errors(ErrorsEnumDto.SHOP.name(), "shop object empty");
            lists.add(shopEmpty);
            ErrorService.saveError(shopEmpty);
            throw new MyException("Shop data are incorrect", lists);
        }

        if (StringUtils.isEmpty(shopDto.getName()) || !ValidateService.nameIsCorrect(shopDto.getName())) {
            lists.add(new Errors(ErrorsEnumDto.SHOP.name(), "shop name is incorrect"));
        }

        if (StringUtils.isEmpty(countryDto.getName()) || !ValidateService.nameIsCorrect(countryDto.getName())) {
            lists.add(new Errors(ErrorsEnumDto.SHOP.name(), "shop name is incorrect"));
        }

        if (!lists.isEmpty()) {
            ErrorService.saveError(shopError);

            lists.stream().forEach(item -> LOGGER.warning(item.getDate() + " " + item.getMessage()));
            throw new MyException("Add shop data are incorrect", lists);
        }
    }

    private void enterShopData(ShopDto shopDto, CountryDto countryDto) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the name of the store: ");
        shopDto.setName(sc.nextLine());

        System.out.println("Enter the name of the country: ");
        countryDto.setName(sc.nextLine());
    }

}
