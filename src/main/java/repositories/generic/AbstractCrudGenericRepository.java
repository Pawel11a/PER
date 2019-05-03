package repositories.generic;

import exceptions.MyException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Id;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AbstractCrudGenericRepository<T, ID> implements CrudGenericRepository<T, ID> {


    protected final EntityManagerFactory entityManagerFactory = DBConnection.getDbConnection().getEntityManagerFactory();

    //sprawdzam co to za Typ
    private final Class<T> entityType = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    @Override
    public void saveOrUpdate(T data) {

        if (data == null) {
            throw new MyException("AbstractCrudGenericRepository - saveOrUpdate method exception, data is null");
        }
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(data);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("AbstractCrudGenericRepository - saveOrUpdate method exception, rollback operation");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }

    @Override
    public Optional<T> findById(ID id) {

        if (id == null) {
            throw new MyException("AbstractCrudGenericRepository - findById method exception, data is null");
        }
        Optional<T> item = Optional.empty();
        EntityManager entityManager = null;
        EntityTransaction transaction = null;


        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            item = Optional.ofNullable(entityManager.find(entityType, id));
            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("AbstractCrudGenericRepository - findById method exception, rollback operation id is {}" + id);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return item;
    }

    @Override
    public List<T> findAll() {

        List<T> items = new ArrayList<>();
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            items = entityManager.createQuery("select e from " + entityType.getCanonicalName() + " e", entityType).getResultList();
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("AbstractCrudGenericRepository - findAll method exception, rollback operation ");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return items;
    }

    @Override
    public void delete(Id id) {

        if (id == null) {
            throw new MyException("AbstractCrudGenericRepository - delete method exception, data is null");
        }
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            T entity = entityManager.find(entityType, id);
            entityManager.remove(entity);
            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("AbstractCrudGenericRepository - delete method exception, rollback operation ");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }

    }
}
