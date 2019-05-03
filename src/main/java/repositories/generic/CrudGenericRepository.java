package repositories.generic;

import javax.persistence.Id;
import java.util.List;
import java.util.Optional;

public interface CrudGenericRepository<T,ID> {

    void saveOrUpdate(T data);
    Optional<T> findById(ID id);
    List<T> findAll();
    void delete(Id id);
}
