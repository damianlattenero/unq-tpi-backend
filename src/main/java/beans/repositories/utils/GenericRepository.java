package beans.repositories.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Interface for generic DAO
 * See: https://github.com/cristianelopez/unq-devapp/blob/master/src/spring-hibernate/src/main/java/ar/edu/unq/repositories/GenericRepository.java
 *
 * @param <T>
 */
public interface GenericRepository<T> {

    void save(T entity);

    void delete(T entity);

    void update(T entity);

    T findById(Serializable id);

    List<T> findAll();

    void deleteById(Serializable id);

    int count();

    List<T> findByExample(T exampleObject);

}
