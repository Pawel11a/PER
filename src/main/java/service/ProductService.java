package service;

import convert.Mapper;
import dto.CategoryDto;
import dto.CountryDto;
import dto.ErrorsEnumDto;
import dto.ProducerDto;
import dto.ProductDto;
import exceptions.MyException;
import lombok.RequiredArgsConstructor;
import model.Category;
import model.Errors;
import model.Producer;
import model.Product;
import model.Trade;
import org.apache.commons.lang3.StringUtils;
import repositories.impl.CategoryRepositoryImpl;
import repositories.impl.CountryRepositoryImpl;
import repositories.impl.ProducerRepositoryImpl;
import repositories.impl.ProductRepositoryImpl;
import utils.UtilsMethods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class ProductService {

    private final CountryRepositoryImpl countryRepository;
    private final ProductRepositoryImpl productRepository;
    private final CategoryRepositoryImpl categoryRepository;
    private final ProducerRepositoryImpl producerRepository;

    private final static Logger LOGGER = Logger.getLogger("ProductService");

    public void addProduct() {

//        categoryRepository.saveOrUpdate(new Category("FOOD"));
//        categoryRepository.saveOrUpdate(new Category("TOYS"));
//        categoryRepository.saveOrUpdate(new Category("CLOTHES"));

        LOGGER.info("Start operation addProduct()");

        ProducerDto producerDto = new ProducerDto();
        ProducerDto producerDto1 = new ProducerDto();
        ProductDto productDto = new ProductDto();
        CategoryDto categoryDto = new CategoryDto();

        productDto.setName("LEGO");
        productDto.setPrice(new BigDecimal(15));
        productDto.setCategoryName("ELECTRONIC");

        productDto.setProducerName("SONY");
        productDto.setProducerNameCountry("ENGLAND");

        validateProduct(productDto);
//TODO name guarantee
//        Producer findProducer = producerRepository.findByNameAndCountry(producerDto);


        //nazwa,kategoria,ten sam producent

        Product findProduct = productRepository.findProductAndCategoryAndProducer(productDto);
        if (findProduct != null) {
            Errors productError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCT.name() + " add product");
            ErrorService.saveError(productError);
            throw new MyException("The same product is exists in DB", productError);
        } else {
            productRepository.saveOrUpdate(fromProductDtoToProduct(productDto));
            LOGGER.info("End operation addProduct(), saved new product : " + productDto.getName());
        }


    }

    private void enterProductData(ProductDto productDto, ProducerDto producerDto) {


        Scanner sc = new Scanner(System.in);

        System.out.println("Enter product name: ");
        productDto.setName(sc.nextLine());
        System.out.println("Enter price: ");
        try {
            BigDecimal price = sc.nextBigDecimal();
            productDto.setPrice(price);
        } catch (Exception ex) {
            LOGGER.warning("Incorrect price " + ex);
            productDto.setPrice(null);
        }
        sc.nextLine();
        System.out.println("Enter producer name of product: ");
        String producerName = sc.nextLine();
        productDto.setProducerName(producerName);
//        producerDto.setName(producerName);

        System.out.println("Enter producer country of product: ");
        String producerNameCountry = sc.nextLine();
        productDto.setProducerNameCountry(producerNameCountry);
//        producerDto.setCountry(new CountryDto(producerNameCountry));

        System.out.println("Enter product's category: ");
        String categoryName = sc.nextLine();
        productDto.setCategoryName(categoryName);
//        producerDto.setTrade(Mapper.fromTradeToTradeDto(new Trade(categoryName)));

    }

    private void validateProduct(ProductDto productDto) {

        List<Errors> lists = new ArrayList<>();

        Errors productError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCT.name() + " - add product");
        if (productDto == null) {
            Errors productEmpty = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCT.name() + " - product objectis empty");
            lists.add(productEmpty);
            ErrorService.saveError(productError);
            throw new MyException("Product data are incorrect", lists);
        }

        if (StringUtils.isEmpty(productDto.getName()) || !ValidateService.nameIsCorrect(productDto.getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCT.name() + " - product name is incorrect"));
        }

        if (productDto.getPrice() == null || productDto.getPrice().compareTo(new BigDecimal("0")) == -1) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCT.name() + " - price is incorrect"));
        }

        if (StringUtils.isEmpty(productDto.getProducerNameCountry()) || !ValidateService.nameIsCorrect(productDto.getProducerNameCountry())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCT.name() + " - producer name is incorrect"));
        }

        if (StringUtils.isEmpty(productDto.getProducerName()) || !ValidateService.nameIsCorrect(productDto.getProducerName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCT.name() + " - producer's country name is incorrect"));
        }

        if (StringUtils.isEmpty(productDto.getCategoryName()) || !ValidateService.nameIsCorrect(productDto.getCategoryName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCT.name() + " - producer's category name is incorrect"));
        }

        if (!lists.isEmpty()) {
            ErrorService.saveError(productError);

            lists.stream().forEach(item -> LOGGER.warning(item.getDate() + " " + item.getMessage()));
            throw new MyException("Add product data are incorrect", lists);
        }
    }

    public Product fromProductDtoToProduct(ProductDto productDto) {

        Category byName = categoryRepository.findByName(productDto.getCategoryName());
        Producer byProducer = producerRepository.findByNameAndCountry(productDto.getProducerName(), productDto.getProducerNameCountry());

        if (byName == null || byProducer == null) {
            Errors productError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCT.name() + " add product");
            ErrorService.saveError(productError);
            throw new MyException("producer or category doesn't exists in DB", productError);
        }

        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setCategory(byName);
        product.setProducer(byProducer);

        return product;
    }

}
