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


//        ValidateService.nameIsCorrect("A");

        CategoryService categoryService = new CategoryService(categoryRepository);
        CustomerService customerService = new CustomerService(customerRepository, countryRepository);




        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("ELEKTRONIKA");

        CountryDto countryDto = new CountryDto();
        countryDto.setName("POLSKA");

        CustomerDto customerDto = new CustomerDto();
        customerDto.setName("JAN");
        customerDto.setSurname("KOWAL");
        customerDto.setAge(18);
        customerDto.setCountryName("POLAND");

        try {

            customerService.addCustomer();
        } catch (MyException me) {
            LOGGER.warning(me + "");
        }

        try {
            categoryService.addCategory(categoryDto);
        } catch (MyException me) {
            LOGGER.warning(me + "");
        }
        /*
                var countryRepository = new CountryRepositoryImpl();
        var companyRepository = new CompanyRepositoryImpl();
        var appService = new AppService(countryRepository, companyRepository);
         */

    }

}
