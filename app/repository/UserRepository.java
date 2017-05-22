package repository;

import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import models.AppUser;
import org.h2.engine.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.HibernateEntityManager;
import play.Logger;
import play.db.jpa.JPA;


/**
 * Created by lapa on 5/6/17.
 */
//@ImplementedBy(UserRepositoryImplementation.class)
public class UserRepository extends RepositoryImplementation<AppUser> {

    @Inject
    public UserRepository(Class<AppUser> entityClass) {
        super(entityClass);
    }

//    public Criteria getCriteria() {
//        Session session = ((HibernateEntityManager) JPA.em()).getSession();
//        return session.createCriteria(AppUser.class);
//    }

    public AppUser findUserByEmail(String email) {
        AppUser user = (AppUser) getCriteria().add(Restrictions.eq("email", email)).uniqueResult();
        Logger.info(user.getEmail() + "898798798797979789987979798");
        return user;
    }

}
