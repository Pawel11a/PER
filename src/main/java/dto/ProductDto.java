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

    public ProductDto(String name) {
        this.name = name;
    }

    private Long id;
    private String name;
    private BigDecimal price;
    private CategoryDto categoryDto;
    private ProducerDto producerDto;
}
