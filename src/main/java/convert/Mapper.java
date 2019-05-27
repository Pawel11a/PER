package convert;

import dto.CategoryDto;
import dto.CountryDto;
import dto.CustomerDto;
import model.Category;
import model.Country;
import model.Customer;

import java.util.HashSet;

public class Mapper {

    public static CountryDto fromCountryToCountryDto(Country country) {
        return country == null ? null : CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }

    public static Country fromCountryDtoToCountry(CountryDto countryDto) {
        return countryDto == null ? null : Country.builder()
                .id(countryDto.getId())
                .name(countryDto.getName())
                .customers(new HashSet<>())
                .producers(new HashSet<>())
                .shops(new HashSet<>())
                .build();
    }

    public static CustomerDto fromCustomerToCustomerDto(Customer customer) {
        return customer == null ? null : CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .build();
    }

    public static Customer fromCustomerDtoToCustomer(CustomerDto customerDto) {
        return customerDto == null ? null : Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .age(customerDto.getAge())
                .country(fromCountryDtoToCountry(customerDto.getCountry()))
                .customerOrders(new HashSet<>())
                .build();
    }

    public static CategoryDto fromCategoryToCategoryDto(Category category) {
        return category == null ? null : CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

//    public static Category fromCategoryToCategoryDto(CategoryDto categoryDto) {
//        return categoryDto == null ? null : CategoryDto.builder()
//                .id(categoryDto.getId())
//                .name(categoryDto.getName())
//                .build();
//    }

    /*
       private static HealtCardDto fromHealtCardToHealtCardDto(HealtCard healtCard) {
        return healtCard == null ? null : HealtCardDto.builder()
                .id(healtCard.getId())
                .examinationDate(healtCard.getExaminationDate())
                .notes(healtCard.getNotes())
                .playerDto(healtCard.getPlayer() == null ? null :
                        fromPlayerToPlayerDto(healtCard.getPlayer()))
                .build();
    }

    private static HealtCard fromHealtCardDtoToHealtCard(HealtCardDto healtCardDto) {
        return healtCardDto == null ? null : HealtCard.builder()
                .id(healtCardDto.getId())
                .examinationDate(healtCardDto.getExaminationDate())
                .notes(healtCardDto.getNotes())
                .player(healtCardDto.getPlayerDto() == null ? null :
                        fromPlayerDtoToPlayer(healtCardDto.getPlayerDto()))
                .build();
    }
     */

}
