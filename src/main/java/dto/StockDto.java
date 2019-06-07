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

    private BigInteger id;
    private Integer quantity;
//    private ProductDto productDto;
    private String productName;
    private ShopDto shopDto;

}
