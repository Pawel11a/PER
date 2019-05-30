package repositories.impl;

import dto.ProductDto;
import exceptions.MyException;
import model.Product;
import repositories.ProductRepository;
import repositories.generic.AbstractCrudGenericRepository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProductRepositoryImpl extends AbstractCrudGenericRepository<Product, BigInteger> implements ProductRepository {

    private static Logger LOGGER = Logger.getLogger("ProductRepositoryImpl");

    public Product findProductAndCategoryAndProducer(ProductDto productDto) {

        List<Product> products = new ArrayList<>();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            entityTransaction.commit();


        } catch (Exception ex) {
            LOGGER.warning("an error occurred whether the producer exists " + ex);
            throw new MyException("ProductRepository - findProductAndCategoryAndProducer method exception, rollback operation");

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        if (products.isEmpty()) {
            return null;

        } else {
            return products.get(0);
        }


    }

}
