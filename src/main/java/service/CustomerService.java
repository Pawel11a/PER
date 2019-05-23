package service;

import dto.CategoryDto;
import dto.CustomerDto;
import dto.Errors;
import dto.ErrorsEnum;
import exceptions.MyException;
import lombok.RequiredArgsConstructor;
import map.ModelMappers;
import model.Category;
import model.Customer;
import org.apache.commons.lang3.StringUtils;
import repositories.impl.CategoryRepositoryImpl;
import repositories.impl.CountryRepositoryImpl;
import repositories.impl.CustomerRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class CustomerService {


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



        if(!StringUtils.isEmpty(countryName)){
//            countryRepository
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

        List<Customer> findByName = customerRepository.findByNameAndSurnameAndCountry(customerDto);

        if (findByName == null || findByName.isEmpty()) {

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

        if(customerDto == null){

            lists.add(new Errors(ErrorsEnum.CUSTOMER, "customer objectis empty", "add-customer-100"));
            throw new MyException("Customer data are incorrect", lists);
        }

        if(StringUtils.isEmpty(customerDto.getName()) || !ValidateService.nameIsCorrect(customerDto.getName())){
            lists.add(new Errors(ErrorsEnum.CUSTOMER, "customer name is incorrect", "add-customer-100"));
        }

        if(StringUtils.isEmpty(customerDto.getSurname()) || !ValidateService.nameIsCorrect(customerDto.getSurname())){
            lists.add(new Errors(ErrorsEnum.CUSTOMER, "customer surname is incorrect", "add-customer-200"));
        }

//        if(customerDto.getCountryDto()){
//
//        }


        if(lists.isEmpty()){
            throw new MyException("Customer data are incorrect", lists);
        }


    }
}
