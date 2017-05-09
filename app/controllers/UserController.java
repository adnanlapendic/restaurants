package controllers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import models.AppUser;
import models.Response;
import models.Secured;

import play.Configuration;
import play.Logger;
import play.api.Play;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;
import repository.UserRepository;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.net.HttpCookie;
import java.util.UUID;


/**
 * Created by lapa on 4/18/17.
 */
public class UserController extends Controller {

    private Configuration configuration;
    private UserRepository userRepository;

    @Inject
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Inject
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    @Transactional(readOnly = true)
    public Result login() {

        Form<AppUser> boundForm = AppUser.userForm.bindFromRequest();

        if (boundForm.hasErrors()) {
            return badRequest(Response.errorResponse(boundForm.toString()));
        }

        AppUser user = AppUser.findUserByEmail(boundForm.get().getUsername());

        if (user == null) {
            return unauthorized(Response.errorResponse("Invalid username or password."));
        }

        return ok(Json.newObject().put("access_token", getToken(user)));
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

    @Transactional()
    public Result registerNewUser() {
        Form<AppUser> boundForm = AppUser.userForm.bindFromRequest();

        AppUser user = boundForm.bindFromRequest().get();


        if (boundForm.hasErrors()) {
            return badRequest(Response.errorResponse(boundForm.toString()));
        }

        if (user.getFirstName().equals("") || user.getLastName().equals("") || user.getEmail().equals("") || user.getPhone().equals("") || user.getPassword().equals("") || user.getConfirmPassword().equals("")) {
            return badRequest(Response.errorResponse("All fields are required."));
        }

        String password = user.getPassword();
        String confirmPassword = user.getConfirmPassword();

        if (!password.equals(confirmPassword)) {
            return badRequest(Response.errorResponse("Password and Confirm Password must be equal."));
        }

        user.setToken(UUID.randomUUID().toString());
        userRepository.create(user);
        return ok(Json.toJson(user));

    }

//                user.setToken(UUID.randomUUID().toString());
//            //AppUser.saveUser(user);
//            userRepository.create(user);
//            return ok(Json.toJson(user));


    @Transactional(readOnly = true)
    public Result getCurrentUser() {
        Http.Context ctx = Http.Context.current();
        String email = ctx.session().get("email");
        AppUser user = null;

        if (email != null) {
            user = AppUser.findUserByEmail(email);
        }
        if (user == null) {
            return ok();
        }
        return ok(Json.toJson(user));
    }

    public Result clearSession() {
        ctx().session().clear();
        return ok("session cleared");
    }


}

