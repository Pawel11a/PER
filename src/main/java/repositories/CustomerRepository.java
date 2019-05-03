package repositories;

import model.Customer;
import repositories.generic.CrudGenericRepository;

import java.math.BigInteger;

public interface CustomerRepository extends CrudGenericRepository<Customer, BigInteger> {
}
