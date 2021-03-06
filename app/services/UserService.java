package services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.inject.Singleton;
import models.AppUser;
import models.Login;
import models.Response;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.HibernateEntityManager;
import play.Configuration;
import play.Logger;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.JPA;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import repository.UserRepository;
import scala.App;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;
import static play.mvc.Results.unauthorized;

/**
 * Created by lapa on 5/17/17.
 */
@Singleton
public class UserService implements BaseService {

    private UserRepository userRepository;
    private Configuration configuration;


    @Inject
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }


    @Inject
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public AppUser getCurrentUser() {
        Http.Context ctx = Http.Context.current();
        String token = "dca14341-9035-43f7-8b0b-2f3fa8f52110";
        AppUser user = null;

        if (token != null) {
            user = userRepository.findUserByToken(token);
        }
        if (user == null) {
            return null;
        }

        return user;

    }


    public AppUser findUserByEmail(String email) {

        AppUser user = userRepository.findUserByEmail(email);

        return user;
    }


    public String getToken(AppUser user) {
        try {
            return JWT.create()
                    .withClaim("name", user.getFirstName())
                    .withClaim("email", user.getEmail())
                    .sign(Algorithm.HMAC256(configuration.getString("play.crypto.secret")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public AppUser saveUser(AppUser user) {

        userRepository.create(user);

        return user;
    }


    public AppUser authenticate(String email, String password) {

        AppUser user = (AppUser) userRepository.getCriteria().add(Restrictions.eq("email", email)).uniqueResult();

        if (user != null && user.getPassword().equals(password)) {

            return user;

        } else {

            return null;
        }
    }

    public List getAllUsers() {
        return userRepository.findAll();
    }

    public AppUser updateUser(AppUser user) {
        return userRepository.update(user);
    }

    public void deleteUser(Long id) {
        AppUser user = userRepository.findById(id);
        userRepository.delete(user);
    }

    public AppUser getUserDetails(Long id){
        AppUser user = userRepository.findById(id);
        return user;
    }
}
