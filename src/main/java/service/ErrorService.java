package service;

import exceptions.MyException;
import model.Errors;
import repositories.impl.CustomerOrderRepositoryImpl;
import repositories.impl.ErrorRepositoryImpl;

import java.util.List;
import java.util.logging.Logger;

public class ErrorService {

    private static Logger LOGGER = Logger.getLogger("ErrorService");

    public static void saveError(Errors errors){
        ErrorRepositoryImpl errorRepository = new ErrorRepositoryImpl();

        try {
            errorRepository.saveOrUpdate(errors);
        }catch(Exception ex){
            LOGGER.warning("Error save error object: " + errors);
            throw new MyException("Error try to save object to DB in method: saveError ");
        }
    }

//    public static void saveError(List<Errors> errors){
//        ErrorRepositoryImpl errorRepository = new ErrorRepositoryImpl();
//
//        try {
//            errorRepository.saveOrUpdate(errors);
//        }catch(Exception ex){
//            LOGGER.warning("Error save error object: " + errors);
//            throw new MyException("Error try to save object to DB in method: saveError ");
//        }
//    }

}
