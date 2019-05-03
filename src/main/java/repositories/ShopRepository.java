package repositories;

import model.Shop;
import repositories.generic.AbstractCrudGenericRepository;
import repositories.generic.CrudGenericRepository;

import java.math.BigInteger;

public interface ShopRepository extends CrudGenericRepository<Shop, BigInteger> {
}
