package repositories.impl;

import dto.CustomerDto;
import exceptions.MyException;
import model.Customer;
import repositories.CustomerRepository;
import repositories.generic.AbstractCrudGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CustomerRepositoryImpl extends AbstractCrudGenericRepository<Customer, BigInteger> implements CustomerRepository {

    private static Logger LOGGER = Logger.getLogger("CustomerRepositoryImpl");

    public Customer findByNameAndSurnameAndCountry(CustomerDto customerDto) {

        List<Customer> entity = new ArrayList<>();
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();

            entity = entityManager.createQuery("select c from Customer c " +
                    " where lower(c.name) like :name and lower(c.surname)" +
                    " like :surname AND lower(c.country.name) like :country ", Customer.class)
                    .setParameter("name", customerDto.getName().trim().toLowerCase())
                    .setParameter("surname", customerDto.getSurname().trim().toLowerCase())
                    .setParameter("country", customerDto.getCountry().getName().trim().toLowerCase())
                    .getResultList();
            transaction.commit();


//            entity = entityManager.createQuery("select c from customer as c left join country as cc on c.country_id = cc.id  " +
//                    " where lower(c.name) like :name and lower(c.surname)" +
//                    " like :surname AND lower(cc.name) like :country ", Customer.class)

            if (entity.isEmpty()) {
                return null;
            }
            return entity.get(0);


        } catch (Exception e) {
            LOGGER.warning("Error executed query - findByNameAndSurnameAndCountry, " + e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new MyException("CategoryRepository - findByName method exception, rollback operation");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
