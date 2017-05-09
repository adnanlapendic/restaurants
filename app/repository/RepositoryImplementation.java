package repository;

import models.AppUser;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import play.db.jpa.JPA;

/**
 * Created by lapa on 5/6/17.
 */
public class RepositoryImplementation<T> implements Repository<T> {

    @Override
    public T findById(Long id) {
        return null;
    }

    @Override
    public T create(T model) {
        JPA.em().persist(model);
        JPA.em().flush();
        return model;
    }

    @Override
    public T update(T model) {
        JPA.em().merge(model);
        JPA.em().flush();
        return model;
    }

    @Override
    public void delete(T model) {
        JPA.em().persist(model);
        JPA.em().flush();
    }

//    @Override
//    public Criteria getCriteria() {
//        Session session = ((HibernateEntityManager) JPA.em()).getSession();
//        return session.createCriteria(Class<T>);
//    }

}
