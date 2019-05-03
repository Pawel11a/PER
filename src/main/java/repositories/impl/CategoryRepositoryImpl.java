package repositories.impl;

import model.Category;
import repositories.CategoryRepository;
import repositories.generic.AbstractCrudGenericRepository;

import java.math.BigInteger;

public class CategoryRepositoryImpl extends AbstractCrudGenericRepository<Category, BigInteger> implements CategoryRepository {
}
