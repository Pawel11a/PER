package convert;

import dto.CountryDto;
import dto.CustomerDto;
import model.Country;
import model.Customer;

public class Converts {

    public static Customer fromCustomerDtoToCustomer(CustomerDto customerDto) {

        return Customer.builder()
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .age(customerDto.getAge())
                .country(fromCountryDtoToCountry(customerDto.getCountry()))
                .build();
    }

    public static CustomerDto fromCustomerToCustomerDto(Customer customer) {

        return CustomerDto.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .age(customer.getAge())
                .country(fromCountryToCountryDto(customer.getCountry()))
                .build();
    }

    public static Country fromCountryDtoToCountry(CountryDto countryDto) {

        return Country.builder()
                .name(countryDto.getName())
                .customers(countryDto.getCustomers())
                .producers(countryDto.getProducers())
                .shops(countryDto.getShops())
                .build();

    }

    public static CountryDto fromCountryToCountryDto(Country country) {

        return CountryDto.builder()
                .name(country.getName())
                .customers(country.getCustomers())
                .producers(country.getProducers())
                .shops(country.getShops())
                .build();

    }

}
