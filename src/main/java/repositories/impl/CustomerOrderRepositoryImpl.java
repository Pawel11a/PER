package repositories.impl;

import model.CustomerOrder;
import repositories.CustomerOrderRepository;
import repositories.generic.AbstractCrudGenericRepository;

import java.math.BigInteger;

public class CustomerOrderRepositoryImpl extends AbstractCrudGenericRepository<CustomerOrder, BigInteger> implements CustomerOrderRepository {
}
