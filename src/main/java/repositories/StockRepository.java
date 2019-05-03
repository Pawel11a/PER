package repositories;

import model.Stock;
import repositories.generic.CrudGenericRepository;

import java.math.BigInteger;

public interface StockRepository extends CrudGenericRepository<Stock, BigInteger> {
}
