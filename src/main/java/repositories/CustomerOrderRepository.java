package repositories;

import model.CustomerOrder;
import repositories.generic.CrudGenericRepository;

import java.math.BigInteger;

public interface CustomerOrderRepository extends CrudGenericRepository<CustomerOrder, BigInteger> {
}
