package per;

import dto.CategoryDto;
import dto.CountryDto;
import dto.CustomerDto;
import exceptions.MyException;
import model.Customer;
import repositories.impl.CategoryRepositoryImpl;
import repositories.impl.CountryRepositoryImpl;
import repositories.impl.CustomerRepositoryImpl;
import repositories.impl.ErrorRepositoryImpl;
import service.CategoryService;
import service.CountryService;
import service.CustomerService;
import service.ErrorService;
import service.ValidateService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {
    private static Logger LOGGER = Logger.getLogger("InfoLogging");

    public static void main(String[] args) {
        System.out.println("Hello World!");

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PER");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Repositories and Servicies
        ValidateService validateService = new ValidateService();
        CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
       CountryRepositoryImpl countryRepository = new CountryRepositoryImpl();

//ok dodawanie użytkownika

//        CustomerService customerService = new CustomerService(customerRepository, countryRepository);
//        -----------------------
//        CategoryService categoryService = new CategoryService(categoryRepository);

        CountryService countryService = new CountryService(countryRepository);

        try {
            countryService.addCountry();
//            customerService.addCustomer();
        } catch (MyException me) {
            LOGGER.warning(me + "");
        }

    }

}
