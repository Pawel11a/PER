package convert;

import dto.CategoryDto;
import dto.CountryDto;
import dto.CustomerDto;
import dto.CustomerOrderDto;
import dto.GuaranteeComponentsDto;
import dto.PaymentDto;
import dto.ProducerDto;
import dto.ProductDto;
import dto.ShopDto;
import dto.StockDto;
import dto.TradeDto;
import model.Category;
import model.Country;
import model.Customer;
import model.CustomerOrder;
import model.Errors;
import model.GuaranteeComponents;
import model.Payment;
import model.Producer;
import model.Product;
import model.Shop;
import model.Stock;
import model.Trade;

import java.util.HashSet;

public class Mapper {

    public static CountryDto fromCountryToCountryDto(Country country) {
        return country == null ? null : CountryDto.builder()
                .id(country.getId())
                .name(country.getName())
                .build();
    }

    public static Country fromCountryDtoToCountry(CountryDto countryDto) {
        return countryDto == null ? null : Country.builder()
                .id(countryDto.getId())
                .name(countryDto.getName())
                .customers(new HashSet<>())
                .producers(new HashSet<>())
                .shops(new HashSet<>())
                .build();
    }

    public static CustomerDto fromCustomerToCustomerDto(Customer customer) {
        return customer == null ? null : CustomerDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .age(customer.getAge())
                .country(fromCountryToCountryDto(customer.getCountry()))
                .surname(customer.getSurname())
                .build();
    }

    public static Customer fromCustomerDtoToCustomer(CustomerDto customerDto) {
        return customerDto == null ? null : Customer.builder()
                .id(customerDto.getId())
                .surname(customerDto.getSurname())
                .name(customerDto.getName())
                .age(customerDto.getAge())
                .country(fromCountryDtoToCountry(customerDto.getCountry()))
                .customerOrders(new HashSet<>())
                .build();
    }

    public static CategoryDto fromCategoryToCategoryDto(Category category) {
        return category == null ? null : CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }

    public static Category fromCategoryDtoToCategory(CategoryDto categoryDto) {
        return categoryDto == null ? null : Category.builder()
                .id(categoryDto.getId())
                .name(categoryDto.getName())
                .products(new HashSet<>())
                .build();
    }

    public static CustomerOrderDto fromCustomerOrderToCustomerOrderDto(CustomerOrder customerOrder) {
        return customerOrder == null ? null : CustomerOrderDto.builder()
                .id(customerOrder.getId())
                .discount(customerOrder.getDiscount())
                .date(customerOrder.getDate())
                .quantity(customerOrder.getQuantity())
                .customerDto(fromCustomerToCustomerDto(customerOrder.getCustomer()))
                .paymentDto(fromPaymentToPaymentDto(customerOrder.getPayment()))
                .productDto(fromProductToProductDto(customerOrder.getProduct()))
                .build();
    }

    public static CustomerOrder fromCustomerOrderDtoToCustomerOrder(CustomerOrderDto customerOrderDto) {
        return customerOrderDto == null ? null : CustomerOrder.builder()
                .id(customerOrderDto.getId())
                .discount(customerOrderDto.getDiscount())
                .date(customerOrderDto.getDate())
                .quantity(customerOrderDto.getQuantity())
                .customer(fromCustomerDtoToCustomer(customerOrderDto.getCustomerDto()))
                .payment(fromPaymentDtoToPayment(customerOrderDto.getPaymentDto()))
                .product(fromProductDtoToProduct(customerOrderDto.getProductDto()))
                .build();
    }

    public static Errors fromErrorsDtoToError(dto.Errors errorsDto) {
        return errorsDto == null ? null : Errors.builder()
                .id(errorsDto.getId())
                .date(errorsDto.getDate())
                .message(errorsDto.getMessage())
                .build();
    }

    public static dto.Errors fromErrorsToErrorsDto(Errors errors) {
        return errors == null ? null : dto.Errors.builder()
                .id(errors.getId())
                .date(errors.getDate())
                .message(errors.getMessage())
                .build();
    }

    public static GuaranteeComponents fromGuaranteeComponentsToGuaranteeComponentsDto(GuaranteeComponentsDto guaranteeComponentsDto) {
        return guaranteeComponentsDto == null ? null : GuaranteeComponents.builder()
                .id(guaranteeComponentsDto.getId())
                .product(fromProductDtoToProduct(guaranteeComponentsDto.getProductDto()))
                .guaranteeComponent(guaranteeComponentsDto.getGuaranteeComponent())
                .build();
    }

    public static GuaranteeComponentsDto fromGuaranteeComponentsDtoToGuaranteeComponents(GuaranteeComponents guaranteeComponents) {
        return guaranteeComponents == null ? null : GuaranteeComponentsDto.builder()
                .id(guaranteeComponents.getId())
                .productDto(fromProductToProductDto(guaranteeComponents.getProduct()))
                .guaranteeComponent(guaranteeComponents.getGuaranteeComponent())
                .build();
    }

    public static Payment fromPaymentDtoToPayment(PaymentDto paymentDto) {
        return paymentDto == null ? null : Payment.builder()
                .id(paymentDto.getId())
                .payment(paymentDto.getPayment())
                .build();
    }

    public static PaymentDto fromPaymentToPaymentDto(Payment payment) {
        return payment == null ? null : PaymentDto.builder()
                .id(payment.getId())
                .payment(payment.getPayment())
                .build();
    }

    public static ProducerDto fromProducerToProducerDto(Producer producer) {
        return producer == null ? null : ProducerDto.builder()
                .id(producer.getId())
                .name(producer.getName())
                .countryName(producer.getCountry().getName())
                .tradeName(producer.getTrade().getName())
                .build();
    }

    public static Producer fromProducerDtoToProducer(ProducerDto producerDto) {
        return producerDto == null ? null : Producer.builder()
                .id(producerDto.getId())
                .name(producerDto.getName())
//                .country(fromCountryDtoToCountry(producerDto.getCountryDto()))
//                .trade(fromTradeDtoToTrade(producerDto.getTradeDto()))
                .build();
    }

    public static ProductDto fromProductToProductDto(Product product) {
        return product == null ? null : ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .categoryDto(fromCategoryToCategoryDto(product.getCategory()))
                .producerDto(fromProducerToProducerDto(product.getProducer()))
                .build();
    }

    public static Product fromProductDtoToProduct(ProductDto productDto) {
        return productDto == null ? null : Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .price(productDto.getPrice())
                .category(fromCategoryDtoToCategory(productDto.getCategoryDto()))
                .producer(fromProducerDtoToProducer(productDto.getProducerDto()))
                .build();
    }


    public static Shop fromShopDtoToShop(ShopDto shopDto) {
        return shopDto == null ? null : Shop.builder()
                .id(shopDto.getId())
                .name(shopDto.getName())
                .country(fromCountryDtoToCountry(shopDto.getCountryDto()))
                .build();
    }

    public static ShopDto fromShopToShopDto(Shop shop) {
        return shop == null ? null : ShopDto.builder()
                .id(shop.getId())
                .name(shop.getName())
                .countryDto(fromCountryToCountryDto(shop.getCountry()))
                .build();
    }

    public static Stock fromStockDtoToStock(StockDto stockDto) {
        return stockDto == null ? null : Stock.builder()
                .id(stockDto.getId())
                .shop(fromShopDtoToShop(stockDto.getShopDto()))
                .quantity(stockDto.getQuantity())
                .product(fromProductDtoToProduct(stockDto.getProductDto()))
                .build();
    }

    public static StockDto fromStockToStockDto(Stock stock) {
        return stock == null ? null : StockDto.builder()
                .id(stock.getId())
                .shopDto(fromShopToShopDto(stock.getShop()))
                .quantity(stock.getQuantity())
                .productDto(fromProductToProductDto(stock.getProduct()))
                .build();
    }

    public static TradeDto fromTradeToTradeDto(Trade trade) {
        return trade == null ? null : TradeDto.builder()
                .id(trade.getId())
                .name(trade.getName())
                .build();
    }

    public static Trade fromTradeDtoToTrade(TradeDto tradeDto) {
        return tradeDto == null ? null : Trade.builder()
                .id(tradeDto.getId())
                .name(tradeDto.getName())
                .producers(new HashSet<>())
                .build();
    }

}
