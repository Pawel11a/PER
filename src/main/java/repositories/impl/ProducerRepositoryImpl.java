package repositories.impl;

import dto.ProducerDto;
import exceptions.MyException;
import model.Producer;
import repositories.ProducerRepository;
import repositories.generic.AbstractCrudGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ProducerRepositoryImpl extends AbstractCrudGenericRepository<Producer, BigInteger> implements ProducerRepository {

    private static Logger LOGGER = Logger.getLogger("ProducerRepositoryImpl");

    public Producer findByNameAndCountryAndTrade(ProducerDto producerDto) {

        List<Producer> producers = new ArrayList<>();
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;

        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            producers = entityManager.createQuery("select p from Producer p where lower (p.name) like :name and lower(p.country.name) like :country and lower(p.trade.name) like :trade", Producer.class)
                    .setParameter("name", producerDto.getName().trim().toLowerCase())
                    .setParameter("country", producerDto.getCountry().getName().trim().toLowerCase())
                    .setParameter("trade", producerDto.getTrade().getName().trim().toLowerCase())
                    .getResultList();

            entityTransaction.commit();

        } catch (Exception ex) {

            LOGGER.warning("an error occurred whether the producer exists " + ex);
            throw new MyException("ProducerRepository - findByNameAndCountryAndTrade method exception, rollback operation");

        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }


        if (producers.isEmpty()) {
            return null;
        } else {
            return producers.get(0);
        }


    }
}
