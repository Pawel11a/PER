package repositories.impl;

import dto.ProductDto;
import exceptions.MyException;
import model.Producer;
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

            products = entityManager.createQuery("select p from Product p where lower (p.name) like :name and lower(p.category.name) like :category and lower(p.producer.name) like :producerName", Product.class)
                    .setParameter("name", productDto.getName().trim().toLowerCase())
                    .setParameter("category", productDto.getCategoryDto().getName().trim().toLowerCase())
                    .setParameter("producerName", productDto.getProducerDto().getName().trim().toLowerCase())
                    .getResultList();


            entityTransaction.commit();


        } catch (Exception ex) {
            LOGGER.warning("an error occurred whether the product exists " + ex);
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
