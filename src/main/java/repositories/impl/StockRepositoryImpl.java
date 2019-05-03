package repositories.impl;

import model.Stock;
import repositories.StockRepository;
import repositories.generic.AbstractCrudGenericRepository;

import java.math.BigInteger;

public class StockRepositoryImpl extends AbstractCrudGenericRepository<Stock, BigInteger> implements StockRepository {
}
