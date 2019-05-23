package repositories;

import model.Errors;
import repositories.generic.CrudGenericRepository;

import java.math.BigInteger;

public interface ErrorRepository extends CrudGenericRepository<Errors, BigInteger> {


}
