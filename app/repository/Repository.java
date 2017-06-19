package repository;

import models.AppUser;
import org.hibernate.Criteria;

import java.util.List;

/**
 * Created by lapa on 5/6/17.
 */
public interface Repository<T> {

    T findById(Long id);

    T create(T model);

    T update(T model);

    void delete(T model);

//    List<T> findAll(T model);

}
