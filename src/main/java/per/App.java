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
import repositories.impl.ProducerRepositoryImpl;
import repositories.impl.TradeRepositoryImpl;
import service.CategoryService;
import service.CountryService;
import service.CustomerService;
import service.ErrorService;
import service.ProducerService;
import service.ValidateService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {
    private static Logger LOGGER = Logger.getLogger("InfoLogging");

    public static void main(String[] args) throws IOException {
        System.out.println("Hello World!");

        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("PER");

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        //Repositories and Servicies
        ValidateService validateService = new ValidateService();
        CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        CountryRepositoryImpl countryRepository = new CountryRepositoryImpl();
        ProducerRepositoryImpl producerRepository = new ProducerRepositoryImpl();
        TradeRepositoryImpl tradeRepository = new TradeRepositoryImpl();

//ok dodawanie użytkownika

        CustomerService customerService = new CustomerService(customerRepository, countryRepository);
//        -----------------------
//        CategoryService categoryService = new CategoryService(categoryRepository);

        CountryService countryService = new CountryService(countryRepository);
        ProducerService producerService = new ProducerService(producerRepository, countryRepository, tradeRepository);

//        Boolean continueWork = true;

//        while(continueWork){
//
//        }


        Scanner in = new Scanner(System.in);

        int continueWork = menu();

        while (continueWork != 0) {
            try {
                switch (continueWork) {
                    case 1:
                        System.out.println("Add producer");
                        producerService.addProducer();
                        System.out.format("End add producer ");

                        break;

                    case 2:
                        System.out.println("Add country");
                        countryService.addCountry();
                        System.out.format("End country ");

                        break;

                    case 3:
                        System.out.println("Add customer");
                        customerService.addCustomer();
                        System.out.format("End customer ");

                        break;

                    default :
                        System.out.println("This operation not exists " + continueWork);
                }
            } catch (MyException me) {
                LOGGER.warning(me + "");
            }

            System.out.println("\nPress Enter, to continue...");
            System.in.read();

            continueWork = menu();
        }


        System.out.println("     ****************************************");
        System.out.println("\n     Koniec programu\n\n");

//        try {
//            producerService.addProducer();
////            countryService.addCountry();
////            customerService.addCustomer();
//        } catch (MyException me) {
//            LOGGER.warning(me + "");
//        }

    }

    public static int menu() {
        System.out.println();
        System.out.println("     ****************************************");
        System.out.println("     *                 MENU                 *");
        System.out.println("     ****************************************");
        System.out.println("     1. Suma");
        System.out.println("     2. Sinus");
        System.out.println("     3. Informaja");
        System.out.println("     0. END");

        Scanner in = new Scanner(System.in);
        int operation = in.nextInt();

        return operation;
    }

}
