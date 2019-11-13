package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.Customer;
import model.Producer;
import model.Shop;

import javax.persistence.OneToMany;
import java.math.BigInteger;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CountryDto {

    private Long id;
    private String name;

}
