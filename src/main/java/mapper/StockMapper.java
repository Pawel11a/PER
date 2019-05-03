package mapper;

import dto.ShopDto;
import dto.StockDto;
import model.Product;
import model.Shop;
import model.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface StockMapper {

    @Mappings({
            @Mapping(source = "shop", target = "shopDto")
    })
    StockDto stockToStockDto(Stock stock);

    @Mappings({
            @Mapping(source = "shopDto", target = "shop")
    })
    Stock stockDtoToStock(StockDto stockDto);
}
