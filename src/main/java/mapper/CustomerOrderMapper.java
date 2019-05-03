package mapper;

import dto.CustomerDto;
import dto.CustomerOrderDto;
import model.Customer;
import model.CustomerOrder;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

public interface CustomerOrderMapper {

    @Mappings({
            @Mapping(source = "customer", target = "customerDto"),
            @Mapping(source = "payment", target = "paymentDto"),
            @Mapping(source = "product", target = "productDto")
    })
    CustomerOrderDto customerOrderToCustomerOrderDto(CustomerOrder customerOrder);

    @Mappings({
            @Mapping(source = "customerDto", target = "customer"),
            @Mapping(source = "paymentDto", target = "payment"),
            @Mapping(source = "productDto", target = "product")
    })
    CustomerOrder customerOrderDtoToCustomerOrder(CustomerOrderDto customerOrderDto);

}
