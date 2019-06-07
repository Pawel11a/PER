package service;

import dto.CountryDto;
import dto.ShopDto;
import lombok.RequiredArgsConstructor;
import repositories.impl.CategoryRepositoryImpl;
import repositories.impl.CountryRepositoryImpl;
import repositories.impl.ProductRepositoryImpl;

import java.util.Scanner;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class CustomerOrderService {

    private static Logger LOGGER = Logger.getLogger("CustomerOrderService");

    private final CountryRepositoryImpl countryRepository;
    private final ProductRepositoryImpl productRepository;
    private final CategoryRepositoryImpl categoryRepository;

    /*
    Nowe zamówienie (tabela customer_order). Pobierane są w postaci
napisów imię, nazwisko oraz nazwa kraju pochodzenia klienta, nazwa
oraz nazwa kategorii produktu, ilość zamówionych sztuk, data
zamówienia, wysokość zastosowanej dla zamówienia zniżki oraz rodzaj
płatności. Należy uwzględnić sytuację, w której podawane ilość sztuk
zamawianego produktu przekracza zapasy magazynowe i zgłosić
wyjątek. Naruszenie zasad poprawnego dodawania zamówienia do tabeli
generuje wyjątek klasy RuntimeException z przygotowanym odpowiednim
komunikatem błędu.
     */

    /*
date
discount
quantity
customer_id
payment_id
product_id
     */
    public void addCustomerOrder(){

//        enterShopData(shopDto, countryDto);

    }


    private void enterShopData(ShopDto shopDto, CountryDto countryDto) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the name of the store: ");
        shopDto.setName(sc.nextLine());

        System.out.println("Enter the name of the country: ");
        countryDto.setName(sc.nextLine());
    }

}
