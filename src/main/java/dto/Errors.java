package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Errors {

    private BigInteger id;
    private LocalDateTime date;
    private String message;
    private String code;

    public Errors(LocalDateTime date, String message, String code) {
        this.date = date;
        this.message = message;
        this.code = code;
    }
}
