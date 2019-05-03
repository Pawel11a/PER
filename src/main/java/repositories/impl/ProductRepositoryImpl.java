package repositories.impl;

import model.Product;
import repositories.ProductRepository;
import repositories.generic.AbstractCrudGenericRepository;

import java.math.BigInteger;

public class ProductRepositoryImpl extends AbstractCrudGenericRepository<Product, BigInteger> implements ProductRepository {
}
