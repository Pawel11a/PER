package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Product;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuaranteeComponentsDto {

    private BigInteger id;
    private Integer guaranteeComponent;
    private ProductDto productDto;
}
