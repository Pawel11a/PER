package repositories.impl;

import model.Trade;
import repositories.TradeRepository;
import repositories.generic.AbstractCrudGenericRepository;

import java.math.BigInteger;

public class TradeRepositoryImpl extends AbstractCrudGenericRepository<Trade, BigInteger> implements TradeRepository {
}
