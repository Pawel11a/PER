package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Stock;

import java.math.BigInteger;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopDto {

    private BigInteger id;
    private String name;
    private CountryDto countryDto;

}
