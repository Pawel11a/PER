package repositories.impl;

import exceptions.MyException;
import model.Country;
import model.Trade;
import repositories.TradeRepository;
import repositories.generic.AbstractCrudGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TradeRepositoryImpl extends AbstractCrudGenericRepository<Trade, BigInteger> implements TradeRepository {
    private static Logger LOGGER = Logger.getLogger("TradeRepositoryImpl");
    public Trade findByNameTrade(String tradeName) {

        List<Trade> entity = new ArrayList<>();
        EntityManager entityManager = null;
        EntityTransaction transaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            transaction = entityManager.getTransaction();
            transaction.begin();
            entity = entityManager.createQuery("select c from Trade c where lower(c.name) like :name ", Trade.class)
                    .setParameter("name", tradeName.trim().toLowerCase())
                    .getResultList();
            transaction.commit();


        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            LOGGER.warning("Error find a trade " + e);
            throw new MyException("CountryRepository - findByNameCountry method exception, rollback operation");
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
        return entity.isEmpty() ? null : entity.get(0);
    }

}
