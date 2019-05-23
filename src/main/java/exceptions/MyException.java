package exceptions;


import model.Errors;

import java.util.List;

public class MyException extends RuntimeException {

    private final String message;
    private final List<Errors> errors;
    private final Errors error;

    public MyException(String message, List<Errors> errors) {
        this.message = message;
        this.errors = errors;
        this.error = null;
    }

    public MyException(String message) {
        this.message = message;
        this.errors = null;
        this.error = null;
    }

    public MyException(String message, Errors error) {
        this.message = message;
        this.errors = null;
        this.error = error;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public List<Errors> getErrors() {
        return errors;
    }


}
