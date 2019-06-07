package repositories;

import model.Product;
import repositories.generic.CrudGenericRepository;

import java.math.BigInteger;

public interface ProductRepository extends CrudGenericRepository<Product, BigInteger> {



}
