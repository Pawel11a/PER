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

public class CustomerRepositoryImpl extends AbstractCrudGenericRepository<Customer, BigInteger> implements CustomerRepository {

    public Customer findByNameAndSurnameAndCountry(CustomerDto customerDto) {

        Customer entity = new Customer();
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entity = entityManager.createQuery("select c from Customer c where lower(c.name) like :name and lower(c.surname) like :surname and lower(c.country) like :country", Customer.class)
                    .setParameter("name", customerDto.getName().trim().toLowerCase())
                    .setParameter("surname", customerDto.getSurname().trim().toLowerCase())
                    .setParameter("country", customerDto.getCountry().getName().trim().toLowerCase())
                    .getSingleResult();
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
