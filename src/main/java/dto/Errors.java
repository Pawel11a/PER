package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Errors {

    private BigInteger id;
    private ErrorsEnum date;
    private String message;
    private String code;

    public Errors(ErrorsEnum date, String message, String code) {
        this.date = date;
        this.message = message;
        this.code = code;
    }
}
