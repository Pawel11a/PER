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
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        StockRepositoryImpl stockRepository = new StockRepositoryImpl();

//ok dodawanie użytkownika

        CustomerService customerService = new CustomerService(customerRepository, countryRepository);
        ShopService shopService = new ShopService(shopRepository, countryRepository);
//        -----------------------
//        CategoryService categoryService = new CategoryService(categoryRepository);

        CountryService countryService = new CountryService(countryRepository);
        ProducerService producerService = new ProducerService(producerRepository, countryRepository, tradeRepository);
        StockService stockService = new StockService(stockRepository);
        ProductService productService = new ProductService(countryRepository,
                productRepository, categoryRepository, producerRepository);
/*
Pobierane są w postaci napisów nazwa produktu wraz z nazwą kategorii, nazwa sklepu wraz z krajem pochodzenia oraz ilość sztuk (ta dana może być pobierana jako liczba). W przypadku większej ilości produktów lub sklepów o podanych właściwościach należy zezwolić użytkownikowi na wybranie jednego z nich.
 */
        Scanner in = new Scanner(System.in);

        int continueWork = menu();

        while (continueWork != 0) {
            try {
                switch (continueWork) {
                    case 1:
                        LOGGER.info("Add producer");
                        producerService.addProducer();
                        System.out.format("End add producer ");
                        LOGGER.info("Add producer");

                        break;

                    case 2:
                        LOGGER.info("Add country");
                        countryService.addCountry();
                        LOGGER.info("End country");

                        break;

                    case 3:
                        LOGGER.info("Add customer");
                        customerService.addCustomer();
                        LOGGER.info("End customer");

                        break;

                    case 4:
                        LOGGER.info("Add shop");
                        shopService.addShop();
                        LOGGER.info("End shop");

                        break;

                    case 5:
                        LOGGER.info("Add product");
                        productService.addProduct();
                        LOGGER.info("End product");

                        break;

                    case 6:
                        LOGGER.info("Add stock");
                        stockService.addStock();
                        LOGGER.info("End stock");

                        break;

                    default:
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
        System.out.println("     5. Add product");
        System.out.println("     0. END");

        Scanner in = new Scanner(System.in);
        int operation = in.nextInt();

        return operation;
    }

}
