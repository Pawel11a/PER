package service;

import dto.CategoryDto;
import dto.CustomerDto;
import exceptions.MyException;
import lombok.RequiredArgsConstructor;
import map.ModelMappers;
import model.Category;
import model.Customer;
import org.apache.commons.lang3.StringUtils;
import repositories.impl.CategoryRepositoryImpl;
import repositories.impl.CustomerRepositoryImpl;

import java.util.List;

@RequiredArgsConstructor
public class CustomerService {


    private final CustomerRepositoryImpl customerRepository;

    public void addCustomer(CustomerDto customerDto) {

        if (customerDto == null) {
            throw new MyException("CustomerService - method addCustomer categoryDto is null");
        } else {
            if (StringUtils.isEmpty(customerDto.getName()) || StringUtils.isEmpty(customerDto.getSurname()) || customerDto.getAge() == null) {
                throw new MyException("CustomerService - method addCustomer name, surname, age is incorect");
            } else {
                if (!ValidateService.nameIsCorrect(customerDto.getName())) {
                    throw new MyException("CustomerService - method addCustomer, nameIsCorrect name is incorrect : " + customerDto.getName());
                } else if (!ValidateService.nameIsCorrect(customerDto.getSurname())) {
                    throw new MyException("CustomerService - method addCustomer, nameIsCorrect surname is incorrect : " + customerDto.getSurname());
                } else if (!ValidateService.ageIsEqualsOrBiggerThan18(customerDto.getAge())) {
                    throw new MyException("CustomerService - method addCustomer, ageIsEqualsOrBiggerThan18 age is incorrect : " + customerDto.getAge());
                }
            }
        }

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
}
