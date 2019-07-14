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
public class CategoryDto {

    public CategoryDto(String name) {
        this.name = name;
    }

    private BigInteger id;
    private String name;
}
