package repositories.impl;

import model.Customer;
import repositories.CustomerRepository;
import repositories.generic.AbstractCrudGenericRepository;

import java.math.BigInteger;

public class CustomerRepositoryImpl extends AbstractCrudGenericRepository<Customer, BigInteger> implements CustomerRepository {
}
