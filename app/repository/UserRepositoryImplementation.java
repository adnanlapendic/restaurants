package repository;

import com.google.inject.ImplementedBy;
import models.AppUser;
import org.hibernate.criterion.Restrictions;

/**
 * Created by lapa on 5/6/17.
 */
public class UserRepositoryImplementation extends RepositoryImplementation<AppUser> implements UserRepository {

//    @Override
//    public AppUser findUserByEmail(String email) {
//        return getCriteria().add(Restrictions.eq("email", email)).uniqueResult();
//    }
}
