package convert;

import dto.CountryDto;
import dto.CustomerDto;
import dto.ProductDto;
import dto.ShopDto;
import dto.StockDto;
import model.Country;
import model.Customer;
import model.Product;
import model.Shop;
import model.Stock;

public class Converts {

    public static Customer fromCustomerDtoToCustomer(CustomerDto customerDto) {

        return Customer.builder()
                .name(customerDto.getName())
                .surname(customerDto.getSurname())
                .age(customerDto.getAge())
                .country(fromCountryDtoToCountry(customerDto.getCountry()))
                .build();
    }

    public static CustomerDto fromCustomerToCustomerDto(Customer customer) {

        return CustomerDto.builder()
                .name(customer.getName())
                .surname(customer.getSurname())
                .age(customer.getAge())
                .country(fromCountryToCountryDto(customer.getCountry()))
                .build();
    }

    public static Country fromCountryDtoToCountry(CountryDto countryDto) {

        return Country.builder()
                .name(countryDto.getName())
                .customers(countryDto.getCustomers())
                .producers(countryDto.getProducers())
                .shops(countryDto.getShops())
                .build();

    }

    public static CountryDto fromCountryToCountryDto(Country country) {

        return CountryDto.builder()
                .name(country.getName())
                .customers(country.getCustomers())
                .producers(country.getProducers())
                .shops(country.getShops())
                .build();

    }

//    public static Shop fromShopDtoToShop(ShopDto shopDto){
//
//        return Shop.builder()
//                .id(shopDto.getId())
//                .name(shopDto.getName())
//                .country(fromCountryDtoToCountry(shopDto.getCountryDto()))
//                .stocks(shopDto.getStocksDto());
//    }
//
//    public static Stock fromStockDtoToStock(StockDto stockDto) {
//
//        return Stock.builder()
//                .id(stockDto.getId())
//                .product(stockDto.getProductDto())
//                .quantity(stockDto.getQuantity())
//                .shop(stockDto.getShopDto())
//                .build();
//
//    }
//
//    public static StockDto fromStockToStockDto(Stock stock) {
//
//        return Stock.builder()
//                .id(stockDto.getId())
//                .product(stockDto.getProductDto())
//                .quantity(stockDto.getQuantity())
//                .shop(stockDto.getShopDto())
//                .build();
//
//    }
//
//    public static ProductDto fromProductToProductDto(Product product) {
//
//        return ProductDto.builder()
//                .id(product.getId())
//                .categoryDto(product.getCategory())
//                .name(product.getName())
//                .price(product.getPrice())
//                .producerDto(product.getProducer())
//                .build();
//
//    }
//
//    public static Product fromProductDtoToProduct(ProductDto productDto) {
//
//        return Product.builder()
//                .id(product.getId())
//                .category(product.getCategory())
//                .name(product.getName())
//                .price(product.getPrice())
//                .producerDto(product.getProducer())
//                .build();
//
//    }


}
