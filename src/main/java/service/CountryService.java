package service;

import dto.ErrorsEnumDto;
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
public class CountryService {

    private static Logger LOGGER = Logger.getLogger("CountryService");

    //    private final CustomerRepositoryImpl customerRepository;
    private final CountryRepositoryImpl countryRepository;
    private List<Errors> lists = new ArrayList<>();


    public void addCountry() {
        LOGGER.info("Start CountryService operation: addCountry");

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter country which you want to add: ");
        String countryName = sc.nextLine();
        validation(countryName);

        Country country = new Country(countryName);
        try {
            Country isExistsCountryWithTheSameName = countryRepository.findByNameCountry(country.getName());

            if (isExistsCountryWithTheSameName == null) {
                countryRepository.saveOrUpdate(country);
                LOGGER.info("End operation addCountry(), saved country");
            } else {
                LOGGER.warning("Error when try addCountry to save in DB - country with name: " + country.getName());
                throw new UnsupportedOperationException();
            }
        } catch (Exception ex) {
            LOGGER.warning("Error during saving country: " + ex);
            Errors countryError = new Errors(UtilsMethods.currentDate(),ErrorsEnumDto.COUNTRY.name() + " - add country");
            ErrorService.saveError(countryError);
            throw new MyException("Error when try addCountry to save in DB ", countryError);
        }


        LOGGER.info("End CountryService operation: addCountry");
    }

    private void validation(String countryName) {
        Errors countryError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.COUNTRY.name()+ " - add country");
        if (StringUtils.isEmpty(countryName) || !ValidateService.nameIsCorrect(countryName)) {
            lists.add(new Errors(UtilsMethods.currentDate(),ErrorsEnumDto.COUNTRY.name()+ " - country name is incorrect"));
        }

        if (!lists.isEmpty()) {
            ErrorService.saveError(countryError);

            lists.stream().forEach(item -> LOGGER.warning(item.getDate() + " " + item.getMessage()));
            throw new MyException("Add country data are incorrect", lists);
        }
    }
}
