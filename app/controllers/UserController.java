package controllers;

import models.AppUser;
import play.Logger;

import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import java.util.Map;


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
        AppUser user = AppUser.userForm.bindFromRequest().get();

        AppUser user2 = new AppUser();

        if(user != null){
            user2.setFirstName(user.getFirstName());
            user2.setLastName(user.getLastName());
            user2.setEmail(user.getEmail());
            user2.setPassword(user.getPassword());
            user2.setPhone(user.getPhone());

            AppUser.saveUser(user2);

            return ok(Json.toJson(user2));
        }else{

            return badRequest();
        }

    }

}

