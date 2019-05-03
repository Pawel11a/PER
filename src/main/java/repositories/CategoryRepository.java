package repositories;

import model.Category;
import repositories.generic.CrudGenericRepository;

import java.math.BigInteger;

public interface CategoryRepository extends CrudGenericRepository<Category, BigInteger> {
}
