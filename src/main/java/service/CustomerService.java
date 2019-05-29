package service;


import convert.Mapper;
import dto.CountryDto;
import dto.CustomerDto;
import dto.ErrorsEnumDto;
import exceptions.MyException;
import lombok.RequiredArgsConstructor;
import model.Country;
import model.Customer;
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
public class CustomerService {

    private static Logger LOGGER = Logger.getLogger("CustomerService");

    private final CustomerRepositoryImpl customerRepository;
    private final CountryRepositoryImpl countryRepository;

    public void addCustomer() {

        LOGGER.info("Start operation addCustomer()");

        CustomerDto customerDto = new CustomerDto();
        CountryDto countryDto = new CountryDto();
        enterCustomerData(customerDto, countryDto);

        validateCustomer(customerDto, countryDto);

        Country findCountryByName = countryRepository.findByNameCountry(countryDto.getName());

        if (findCountryByName != null) {

            customerDto.setCountry(Mapper.fromCountryToCountryDto(findCountryByName));

            Customer findExistsTheSameCustomer = customerRepository.findByNameAndSurnameAndCountry(customerDto);

            if (findExistsTheSameCustomer != null) {
                Errors customerError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCER.name() + " add customer");
                ErrorService.saveError(customerError);
                throw new MyException("Customer is exists in DB", customerError);
            }
            try {
                customerRepository.saveOrUpdate(Mapper.fromCustomerDtoToCustomer(customerDto));

                LOGGER.info("End operation addCustomer(), saved customer");
            } catch (Exception ex) {
                Errors customerError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCER.name() + " add customer");
                ErrorService.saveError(customerError);
                throw new MyException("Error when try addCustomer to save in DB ", customerError);
            }
        } else {

            customerDto.setCountry(countryDto);
            customerRepository.saveOrUpdate(Mapper.fromCustomerDtoToCustomer(customerDto));

            LOGGER.info("End operation addCustomer(), saved customer");
        }

    }

    private void enterCustomerData(CustomerDto customerDto, CountryDto countryDto) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Podaj imie klienta: ");
        customerDto.setName(sc.nextLine());
        System.out.println("Podaj nazwisko klienta: ");
        customerDto.setSurname(sc.nextLine());
        System.out.println("Podaj wiek klienta: ");
        Integer age = null;
        try {
            age = sc.nextInt();
        } catch (Exception ex) {
            LOGGER.warning("employee's age is incorrect");
        }
        customerDto.setAge(age);
        sc.nextLine();

        System.out.println("Podaj nazwe kraju: ");
        countryDto.setName(sc.nextLine());
    }

    private void validateCustomer(CustomerDto customerDto, CountryDto countryDto) {

        List<Errors> lists = new ArrayList<>();

        Errors customerError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCER.name() + " - add customer");
        if (customerDto == null) {
            Errors customerEmpty = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCER.name() + " - -customer objectis empty");
            lists.add(customerEmpty);
            ErrorService.saveError(customerError);
            throw new MyException("Customer data are incorrect", lists);
        }

        if (StringUtils.isEmpty(customerDto.getName()) || !ValidateService.nameIsCorrect(customerDto.getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCER.name() + " - customer name is incorrect"));
        }

        if (StringUtils.isEmpty(customerDto.getSurname()) || !ValidateService.nameIsCorrect(customerDto.getSurname())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCER.name() + " - customer surname is incorrect"));
        }

        if (customerDto.getAge() == null || customerDto.getAge() < 18) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCER.name() + " - customer surname is incorrect"));
        }

        if (StringUtils.isEmpty(countryDto.getName()) || !ValidateService.nameIsCorrect(countryDto.getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCER.name() + " - country name is incorrect"));
        }

        if (!lists.isEmpty()) {
            ErrorService.saveError(customerError);

            lists.stream().forEach(item -> LOGGER.warning(item.getDate() + " " + item.getMessage()));
            throw new MyException("Add customer data are incorrect", lists);
        }
    }
}
