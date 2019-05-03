package mapper;

import dto.CategoryDto;
import dto.CustomerDto;
import model.Category;
import model.Customer;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface CustomerMapper {

    @Mappings({
            @Mapping(source = "country", target = "countryDto")
    })
    CustomerDto customerToCustomerDto(Customer customer);

    @Mappings({
            @Mapping(source = "countryDto", target = "country")
    })
    Customer customerDtoToCustomer(CustomerDto customerDto);

}
