package repositories;

import model.Country;
import repositories.generic.AbstractCrudGenericRepository;
import repositories.generic.CrudGenericRepository;

import java.math.BigInteger;

public interface CountryRepository extends CrudGenericRepository<Country, BigInteger> {
}
