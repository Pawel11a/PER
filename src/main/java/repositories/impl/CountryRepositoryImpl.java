package repositories.impl;

import dto.CountryDto;
import dto.CustomerDto;
import exceptions.MyException;
import model.Country;
import model.Customer;
import repositories.CountryRepository;
import repositories.generic.AbstractCrudGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CountryRepositoryImpl extends AbstractCrudGenericRepository<Country, BigInteger> implements CountryRepository {

    private static Logger LOGGER = Logger.getLogger("CountryRepositoryImpl");

    public Country findByNameCountry(String countryName) {

        List<Country> entity = new ArrayList<>();
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entity = entityManager.createQuery("select c from Country c where lower(c.name) like :name ", Country.class)
                    .setParameter("name", countryName.trim().toLowerCase())
                    .getResultList();
            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.warning("Error find a country " + e);
            throw new MyException("CountryRepository - findByNameCountry method exception, rollback operation");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return entity.isEmpty() ? null : entity.get(0);
    }
}
