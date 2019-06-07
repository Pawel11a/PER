package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {

    private BigInteger id;
    private String name;
    private BigDecimal price;
//    private CategoryDto categoryDto;
    private ProducerDto producerDto;
//    private String tradeName;
    private String categoryName;
    private String producerName;
    private String producerNameCountry;
}
