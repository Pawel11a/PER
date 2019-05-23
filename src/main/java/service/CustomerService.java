package service;

import dto.CustomerDto;
import dto.ErrorsEnumDto;
import exceptions.MyException;
import lombok.RequiredArgsConstructor;
import map.ModelMappers;
import model.Country;
import model.Customer;
import model.Errors;
import org.apache.commons.lang3.StringUtils;
import repositories.impl.CountryRepositoryImpl;
import repositories.impl.CustomerRepositoryImpl;

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

        CustomerDto customerDto = new CustomerDto();
        Scanner sc = new Scanner(System.in);


        System.out.println("Podaj imie klienta: ");
        customerDto.setName(sc.nextLine());

        System.out.println("Podaj nazwisko klienta: ");
        customerDto.setSurname(sc.nextLine());

        System.out.println("Podaj wiek klienta: ");
        customerDto.setAge(sc.nextInt());
        sc.nextLine();

        System.out.println("Podaj nazwe kraju: ");
        String countryName = sc.nextLine();

        validateCustomer(customerDto, countryName);


        Country findCountryByName = countryRepository.findByNameCountry(countryName);

        if (findCountryByName != null) {

            customerDto.setCountryName(findCountryByName.getName());

            Customer findExistsTheSameCustomer = customerRepository.findByNameAndSurnameAndCountry(customerDto);

            if (findExistsTheSameCustomer != null) {
                Errors customerError = new Errors(ErrorsEnumDto.CUSTOMER.name(), "add customer");
                ErrorService.saveError(customerError);
                throw new MyException("Customer is exists in DB", customerError);
            }
        }else{
            Country country = new Country(countryName);
            countryRepository.saveOrUpdate(country);

        }


//        customerDto.setCountryDto();

//        if (customerDto == null) {
//            throw new MyException("CustomerService - method addCustomer categoryDto is null");
//        } else {
//            if (StringUtils.isEmpty(customerDto.getName()) || StringUtils.isEmpty(customerDto.getSurname()) || customerDto.getAge() == null) {
//                throw new MyException("CustomerService - method addCustomer name, surname, age is incorect");
//            } else {
//                if (!ValidateService.nameIsCorrect(customerDto.getName())) {
//                    throw new MyException("CustomerService - method addCustomer, nameIsCorrect name is incorrect : " + customerDto.getName());
//                } else if (!ValidateService.nameIsCorrect(customerDto.getSurname())) {
//                    throw new MyException("CustomerService - method addCustomer, nameIsCorrect surname is incorrect : " + customerDto.getSurname());
//                } else if (!ValidateService.ageIsEqualsOrBiggerThan18(customerDto.getAge())) {
//                    throw new MyException("CustomerService - method addCustomer, ageIsEqualsOrBiggerThan18 age is incorrect : " + customerDto.getAge());
//                }
//            }
//        }

        Customer findByName = customerRepository.findByNameAndSurnameAndCountry(customerDto);

        if (findByName == null) {

//            var mapperCategory = Mappers.getMapper(CategoryMapper.class);
//            Category category = mapperCategory.categoryDtoToCategory(categoryDto);
            Customer customer = ModelMappers.fromCustomerDtoToCustomer(customerDto);


            customerRepository.saveOrUpdate(customer);
        } else {
            throw new MyException("CustomerService - method addCustomer name : " + customerDto.getName() + " is exists");
        }


    }

    private void validateCustomer(CustomerDto customerDto, String countryName) {

        List<Errors> lists = new ArrayList<>();

        Errors customerError = new Errors(ErrorsEnumDto.CUSTOMER.name(), "add customer");
        if (customerDto == null) {
            Errors customerEmpty = new Errors(ErrorsEnumDto.CUSTOMER.name(), "customer objectis empty");
            lists.add(customerEmpty);
            ErrorService.saveError(customerError);
            throw new MyException("Customer data are incorrect", lists);
        }

        if (StringUtils.isEmpty(customerDto.getName()) || !ValidateService.nameIsCorrect(customerDto.getName())) {
            lists.add(new Errors(ErrorsEnumDto.CUSTOMER.name(), "customer name is incorrect"));
        }

        if (StringUtils.isEmpty(customerDto.getSurname()) || !ValidateService.nameIsCorrect(customerDto.getSurname())) {
            lists.add(new Errors(ErrorsEnumDto.CUSTOMER.name(), "customer surname is incorrect"));
        }

        if (StringUtils.isEmpty(countryName) || !ValidateService.nameIsCorrect(countryName)) {
            lists.add(new Errors(ErrorsEnumDto.CUSTOMER.name(), "country name is incorrect"));

        }


        if (!lists.isEmpty()) {
            ErrorService.saveError(customerError);

            lists.stream().forEach(item -> LOGGER.warning(item.getDate() + " " +item.getMessage()));
            throw new MyException("Customer data are incorrect", lists);
        }


    }
}
