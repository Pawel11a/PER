package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Country;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProducerDto {

    public ProducerDto(String name) {
        this.name = name;
    }

    private BigInteger id;
    private String name;
    private CountryDto country;
    private TradeDto trade;

}
