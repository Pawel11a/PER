package service;

import dto.CountryDto;
import dto.ErrorsEnumDto;
import dto.ShopDto;
import dto.TradeDto;
import exceptions.MyException;
import lombok.RequiredArgsConstructor;
import model.Country;
import model.Errors;
import org.apache.commons.lang3.StringUtils;
import repositories.impl.CountryRepositoryImpl;
import repositories.impl.CustomerRepositoryImpl;
import utils.UtilsMethods;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class TradeServices {

    private static Logger LOGGER = Logger.getLogger("TradeService");

    private final CustomerRepositoryImpl customerRepository;
    private final CountryRepositoryImpl countryRepository;

    public void addTrade(TradeDto tradeDto) {

        LOGGER.info("Start operation addTrade()");

        ShopDto shopDto = new ShopDto();
        CountryDto countryDto = new CountryDto();

        enterTradeData(shopDto, countryDto);

        validateTrade(tradeDto);

        Country findCountryByName = countryRepository.findByNameCountry(countryDto.getName());

//TODO complete the operation
        if (findCountryByName != null) {


            LOGGER.info("End operation addTrade()");
        }


        LOGGER.info("End operation addTrade()");

    }

    private void validateTrade(TradeDto tradeDto/*, CountryDto countryDto*/) {

        List<Errors> lists = new ArrayList<>();

        Errors tradeError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.TRADE.name() + " - add trade");
        if (tradeError == null) {
            Errors shopEmpty = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.TRADE.name() + " - trade object empty");
            lists.add(shopEmpty);
            ErrorService.saveError(tradeError);
            throw new MyException("Trade data are incorrect", lists);
        }

        if (tradeDto != null || StringUtils.isEmpty(tradeDto.getName()) || !ValidateService.nameIsCorrect(tradeDto.getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.TRADE.name() + " - trade name is incorrect"));
        }

        if (StringUtils.isEmpty(tradeDto.getName()) || !ValidateService.nameIsCorrect(tradeDto.getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.SHOP.name() + " - trade name is incorrect"));
        }

        if (!lists.isEmpty()) {
            ErrorService.saveError(tradeError);

            lists.stream().forEach(item -> LOGGER.warning(item.getDate() + " " + item.getMessage()));
            throw new MyException("Add trade data are incorrect", lists);
        }
    }

    private void enterTradeData(ShopDto shopDto, CountryDto countryDto) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the name of the store: ");
        shopDto.setName(sc.nextLine());

        System.out.println("Enter the name of the country: ");
        countryDto.setName(sc.nextLine());
    }

}
