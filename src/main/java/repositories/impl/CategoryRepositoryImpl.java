package repositories.impl;

import exceptions.MyException;
import model.Category;
import repositories.CategoryRepository;
import repositories.generic.AbstractCrudGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl extends AbstractCrudGenericRepository<Category, BigInteger> implements CategoryRepository {

    public List<Category> findByName(String name) {

        List<Category> entity = new ArrayList<>();
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entity = entityManager.createQuery("select c from Category c where lower(c.name) like :name", Category.class)
                    .setParameter("name", name.trim().toLowerCase())
                    .getResultList();
            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("CategoryRepository - findByName method exception, rollback operation");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return entity;
    }
}
