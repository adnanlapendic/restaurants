package controllers;

import models.AppUser;
import net.sf.ehcache.hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.jpa.HibernateEntityManager;
import play.Logger;

import play.db.jpa.JPA;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import scala.util.parsing.json.JSON;

import javax.persistence.EntityManager;
import java.util.UUID;


/**
 * Created by lapa on 4/18/17.
 */
public class UserController extends Controller {


    @Transactional()
    public Result validateUser() {

        String email = AppUser.userForm.bindFromRequest().get().getEmail();
        String password = AppUser.userForm.bindFromRequest().get().getPassword();

        AppUser user = AppUser.findUserByEmail(email);

        if(user.getPassword().equals(password)){
            return ok(Json.toJson(user));
        } else {
            return badRequest();
        }

    }

    @Transactional()
    public Result registerNewUser(){
        Logger.info("##################33333");
        AppUser user = AppUser.userForm.bindFromRequest().get();

        AppUser user2 = new AppUser();
        Logger.info("##################0222222");

        if(user != null){
//            user2.setId(44444);
            user2.setFirstName(user.getFirstName());
            user2.setLastName(user.getLastName());
            user2.setEmail(user.getEmail());
            user2.setPassword(user.getPassword());
            user2.setPhone(user.getPhone());

            AppUser.saveUser(user2);
            Logger.info("##################555555");

            return ok(Json.toJson(user2));
        }else{
            Logger.info("##################66666");

            return badRequest();
        }

    }


}

