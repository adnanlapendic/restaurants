package repository;

import models.AppUser;
import org.hibernate.criterion.Restrictions;


/**
 * Created by lapa on 5/6/17.
 */
public class UserRepository extends RepositoryImplementation<AppUser> {

    public AppUser findUserByEmail(String email) {
        AppUser user = (AppUser) getCriteria(AppUser.class).add(Restrictions.eq("email", email)).uniqueResult();
        return user;
    }

}
