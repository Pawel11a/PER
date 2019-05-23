package model;


import dto.ErrorsEnumDto;
import lombok.AllArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Objects;

@Entity
public class Errors {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    private String date;
    private String message;

    public Errors() {
    }
    //NAZWA_TABELI;INFORMACJA O BŁĘDZIE.


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Errors(String date, String message) {
        this.date = date;
        this.message = message;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Errors)) return false;
        Errors errors = (Errors) o;
        return Objects.equals(id, errors.id) &&
                Objects.equals(date, errors.date) &&
                Objects.equals(message, errors.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, message);
    }
}
