package repositories.impl;

import exceptions.MyException;
import model.Shop;
import model.Stock;
import repositories.StockRepository;
import repositories.generic.AbstractCrudGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class StockRepositoryImpl extends AbstractCrudGenericRepository<Stock, BigInteger> implements StockRepository {
//    findByNameProductAndShop

    public Stock findByNameProductAndShop(String product, String shop) {

        List<Stock> entity = new ArrayList<>();
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entity = entityManager.createQuery("select c from Stock c where lower(c.product.name) like :product and lower(c.shop.name) like :shop", Stock.class)
                    .setParameter("product", product.trim().toLowerCase())
                    .setParameter("shop", shop.trim().toLowerCase())
                    .getResultList();
            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("ShopRepository - findByName method exception, rollback operation");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

        if (entity == null || entity.isEmpty()) {
            return null;
        }

        return entity.get(0);
    }


}
