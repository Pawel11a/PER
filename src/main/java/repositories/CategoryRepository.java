package repositories;

import exceptions.MyException;
import model.Category;
import repositories.generic.CrudGenericRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.math.BigInteger;
import java.util.Optional;

public interface CategoryRepository extends CrudGenericRepository<Category, BigInteger> {


}
