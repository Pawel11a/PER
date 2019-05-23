package exceptions;

import dto.Errors;

import java.util.List;

public class MyException extends RuntimeException {

    private final String message;
    private final List<Errors> errors;

    public MyException(String message, List<Errors> errors) {
        this.message = message;
        this.errors = errors;
    }

    public MyException(String message) {
        this.message = message;
        this.errors = null;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public List<Errors> getErrors() {
        return errors;
    }


}
