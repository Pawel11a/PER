package service;

import convert.Mapper;
import dto.CountryDto;
import dto.ErrorsEnumDto;
import dto.ProducerDto;
import dto.ProductDto;
import exceptions.MyException;
import lombok.RequiredArgsConstructor;
import model.*;
import org.apache.commons.lang3.StringUtils;
import repositories.impl.CategoryRepositoryImpl;
import repositories.impl.CountryRepositoryImpl;
import repositories.impl.ProducerRepositoryImpl;
import repositories.impl.ProductRepositoryImpl;
import utils.UtilsMethods;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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

        LOGGER.info("Start operation addProduct()");

        ProductDto productDto = new ProductDto();

        enterProductData(productDto);
        validateProduct(productDto);

        //---------------------------

        Product findProduct = productRepository.findProductAndCategoryAndProducer(productDto);

        try{
//TODO complete the operation
        if (findProduct != null) {
            throw new Exception("Product is exists");

        }
        productRepository.saveOrUpdate(Mapper.fromProductDtoToProduct(productDto));

    }catch(Exception ex){
        LOGGER.warning("Incorrect product data " + ex);
        Errors shopError = new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCT.name() + " - add product");
    }

//------------

        LOGGER.info("End operation addProduct()");


    }

    private void enterProductData(ProductDto productDto) {
        Scanner sc = new Scanner(System.in);
        ProducerDto producerDto = new ProducerDto();
        CountryDto countryDto = new CountryDto();

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
        System.out.println("Enter product's producer name: ");
        String producerName = sc.nextLine();
//        productDto.setProducerDto(Mapper.fromProducerToProducerDto(new Producer(producerName)));
        producerDto.setName(producerName);
        System.out.println("Enter product's category: ");
        String categoryName = sc.nextLine();
        productDto.setCategoryDto(Mapper.fromCategoryToCategoryDto(new Category(categoryName)));
        System.out.println("Enter producter's country: ");
        String producterCountry = sc.nextLine();
        countryDto.setName(producerName);
        producerDto.setCountry(countryDto);
        productDto.setProducerDto(producerDto);
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

        if (StringUtils.isEmpty(productDto.getProducerDto().getName()) || !ValidateService.nameIsCorrect(productDto.getProducerDto().getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCT.name() + " - country name is incorrect"));
        }

        if (StringUtils.isEmpty(productDto.getCategoryDto().getName()) || !ValidateService.nameIsCorrect(productDto.getCategoryDto().getName())) {
            lists.add(new Errors(UtilsMethods.currentDate(), ErrorsEnumDto.PRODUCT.name() + " - category is incorrect"));
        }

        if (!lists.isEmpty()) {
            ErrorService.saveError(productError);

            lists.stream().forEach(item -> LOGGER.warning(item.getDate() + " " + item.getMessage()));
            throw new MyException("Add product data are incorrect", lists);
        }
    }

}
