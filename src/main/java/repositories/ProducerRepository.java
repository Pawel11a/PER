package repositories;

import model.Producer;
import repositories.generic.CrudGenericRepository;

import java.math.BigInteger;

public interface ProducerRepository extends CrudGenericRepository<Producer, BigInteger> {
}
