package repository;

import com.google.inject.ImplementedBy;
import models.AppUser;

/**
 * Created by lapa on 5/6/17.
 */
@ImplementedBy(UserRepositoryImplementation.class)
public interface UserRepository extends Repository<AppUser> {

//    AppUser findUserByEmail(String email);

}
