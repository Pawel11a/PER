package repositories.impl;

import model.Producer;
import repositories.ProducerRepository;
import repositories.generic.AbstractCrudGenericRepository;

import java.math.BigInteger;

public class ProducerRepositoryImpl extends AbstractCrudGenericRepository<Producer, BigInteger> implements ProducerRepository {
}
