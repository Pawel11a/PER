package repositories;

import model.Trade;
import repositories.generic.CrudGenericRepository;

import java.math.BigInteger;

public interface TradeRepository extends CrudGenericRepository<Trade, BigInteger> {
}
