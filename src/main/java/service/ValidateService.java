package service;


import exceptions.MyException;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateService {

    private static Logger LOGGER = Logger.getLogger("InfoLogging");
    //    private static final Logger LOGGER = Logger.getLogger(ValidateService.class.getName());
    private List<MyException> validates = new ArrayList<>();

    public static boolean nameIsCorrect(String data) {

        Pattern pattern = Pattern.compile("^[A-Z]+[ ]*");
        Matcher matcher = pattern.matcher(data);

        if (matcher.matches()) {
            LOGGER.warning("object is correct: " + data);
        } else {
            LOGGER.warning("object is incorrect: " + data);
        }
        return matcher.matches();

    }

    public static boolean ageIsEqualsOrBiggerThan18(Integer age) {

        if (age == null || age < 18) {
            return false;
        }
        return true;
    }

}
