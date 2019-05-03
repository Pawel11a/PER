package mapper;

import dto.ProducerDto;
import dto.ProductDto;
import model.Producer;
import model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface ProductMapper {

    @Mappings({
            @Mapping(source = "category", target = "categoryDto"),
            @Mapping(source = "producer", target = "producerDto")
    })
    ProductDto productToProductDto(Product product);

    @Mappings({
            @Mapping(source = "categoryDto", target = "category"),
            @Mapping(source = "producerDto", target = "producer")
    })
    Product productDtoToProduct(ProductDto productDto);

}
