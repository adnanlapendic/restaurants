package models;
import play.Logger;
import play.mvc.*;

/**
 * Created by lapa on 4/26/17.
 */

public class Secured extends Security.Authenticator {
    @Override
    public String getUsername(Http.Context ctx) {
        String email = AppUser.userForm.bindFromRequest().get().getEmail();
        ctx.session().put("email", email);

        return ctx.session().get("email");
    }

        @Override
    public Result onUnauthorized(Http.Context context) {
        return super.onUnauthorized(context);
    }

}
