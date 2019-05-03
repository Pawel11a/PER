package mapper;

import dto.ProductDto;
import dto.ShopDto;
import model.Product;
import model.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ShopMapper {

    @Mappings({
            @Mapping(source = "country", target = "countryDto")
    })
    ShopDto shopToShopDto(Shop shop);

    @Mappings({
            @Mapping(source = "countryDto", target = "country")
    })
    Product shopDtoToShop(ShopDto shopDto);


}
