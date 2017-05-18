package controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import models.AppUser;
import models.Login;
import models.Response;
import models.Secured;

import play.Configuration;
import play.Logger;
import play.api.Play;
import play.data.DynamicForm;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import repository.UserRepository;
import services.UserService;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.util.UUID;


/**
 * Created by lapa on 4/18/17.
 */
public class UserController extends Controller {

    private UserService userService;

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    @Transactional(readOnly = true)
    public Result login() {

        Form<Login> boundForm = Login.loginForm.bindFromRequest();

        AppUser user = userService.findUserByEmail(boundForm.get().getUsername());

        if (user == null) {
            return unauthorized(Response.errorResponse("Invalid username or password."));
        }

        return ok(Json.newObject().put("access_token", userService.getToken(user)));

    }


    @Transactional()
    public Result registerNewUser() {
        Form<AppUser> boundForm = AppUser.userForm.bindFromRequest();

        AppUser user = boundForm.bindFromRequest().get();

        if (boundForm.hasErrors()) {
            return null;
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            return badRequest(Response.errorResponse("Password and Confirm Password must be equal."));
        }

        user.setToken(UUID.randomUUID().toString());

        return ok(Json.toJson(userService.saveUser(user)));

    }


    @Transactional(readOnly = true)
    public Result getCurrentUser() {

        return ok(Json.toJson(userService.getCurrentUser()));
    }

    public Result clearSession() {
        ctx().session().clear();
        return ok("session cleared");
    }


}

