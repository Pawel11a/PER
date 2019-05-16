package map;

import dto.CategoryDto;
import dto.CountryDto;
import dto.CustomerDto;
import model.Category;
import model.Country;
import model.Customer;

import java.util.HashSet;

public interface ModelMappers {

    static CategoryDto fromCountryToCountryDto(Category category) {
        return category == null ? null : CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    static Category fromCountryDtoToCountry(CategoryDto categoryDto) {
        return categoryDto == null ? null : Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .build();
    }

    static CustomerDto fromCustomerToCustomerDto(Customer customer) {
        return customer == null ? null : CustomerDto.builder()
                .id(customer.getId())
                .age(customer.getAge())
                .name(customer.getName())
                .surname(customer.getSurname())
//                .countryDto(customer.getCountry())
                .build();
    }

    static Customer fromCustomerDtoToCustomer(CustomerDto customerDto) {
        return customerDto == null ? null : Customer.builder()
                .id(customerDto.getId())
                .age(customerDto.getAge())
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
//                .country(customerDto.getCountry())
                .build();
    }

}