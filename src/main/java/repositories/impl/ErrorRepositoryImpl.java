package repositories.impl;

import model.Errors;
import repositories.ErrorRepository;
import repositories.generic.AbstractCrudGenericRepository;

import java.math.BigInteger;

public class ErrorRepositoryImpl extends AbstractCrudGenericRepository<Errors, BigInteger> implements ErrorRepository {
}
