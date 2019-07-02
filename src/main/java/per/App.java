package per;

import dto.CategoryDto;
import dto.CountryDto;
import dto.CustomerDto;
import exceptions.MyException;
import model.Customer;
import repositories.impl.*;
import service.*;

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
        ShopRepositoryImpl shopRepository = new ShopRepositoryImpl();

//ok dodawanie użytkownika

        CustomerService customerService = new CustomerService(customerRepository, countryRepository);
        ShopService shopService = new ShopService(shopRepository, countryRepository);
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

                    case 4:
                        System.out.println("Add shop");
                        shopService.addShop();
                        System.out.format("End shop ");

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
        System.out.println("     1. Add producer");
        System.out.println("     2. Add country");
        System.out.println("     3. Add customer");
        System.out.println("     4. Add shop");
        System.out.println("     0. END");

        Scanner in = new Scanner(System.in);
        int operation = in.nextInt();

        return operation;
    }

}
