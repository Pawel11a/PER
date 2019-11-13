package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockDto {

    private Long id;
    private Integer quantity;
    private ProductDto productDto;
    private ShopDto shopDto;

}
