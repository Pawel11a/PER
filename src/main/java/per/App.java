package per;

import service.ValidateService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PER");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        ValidateService validateService = new ValidateService();

        validateService.validCaseAndSpaces("A");

        /*
                var countryRepository = new CountryRepositoryImpl();
        var companyRepository = new CompanyRepositoryImpl();
        var appService = new AppService(countryRepository, companyRepository);
         */

    }
    
}
