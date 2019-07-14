package repositories.impl;

import exceptions.MyException;
import model.Category;
import model.Shop;
import repositories.ShopRepository;
import repositories.generic.AbstractCrudGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ShopRepositoryImpl extends AbstractCrudGenericRepository<Shop, BigInteger> implements ShopRepository {

    public Shop findShopByName(String name) {

        List<Shop> entity = new ArrayList<>();
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entity = entityManager.createQuery("select c from Shop c where lower(c.name) like :name", Shop.class)
                    .setParameter("name", name.trim().toLowerCase())
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

        if (entity == null || entity.get(0) == null) {
            return null;
        }

        return entity.get(0);
    }

}
