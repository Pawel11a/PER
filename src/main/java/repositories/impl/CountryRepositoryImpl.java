package repositories.impl;

import model.Country;
import repositories.CountryRepository;
import repositories.generic.AbstractCrudGenericRepository;

import java.math.BigInteger;

public class CountryRepositoryImpl extends AbstractCrudGenericRepository<Country, BigInteger> implements CountryRepository {
}
