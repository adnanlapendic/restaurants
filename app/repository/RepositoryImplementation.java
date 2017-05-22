package repository;

import com.google.inject.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import play.db.jpa.JPA;

import java.util.List;

/**
 * Created by lapa on 5/6/17.
 */
public class RepositoryImplementation<T> implements Repository<T> {

    public Class<T> entityClass;

    @Inject
    public RepositoryImplementation(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    private Class<T> getEntityClass() {
        return this.entityClass;
    }

    @Override
    public T findById(Long id) {
        return JPA.em().find(getEntityClass(), id);
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

    public Criteria getCriteria() {
        Session session = ((HibernateEntityManager) JPA.em()).getSession();
        return session.createCriteria(getEntityClass());
    }

    @Override
    public List<T> findAll() {
        return getCriteria().list();
    }

}
