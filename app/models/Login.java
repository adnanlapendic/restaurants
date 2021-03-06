package models;

import play.data.Form;
import play.data.validation.Constraints;

/**
 * Created by lapa on 5/18/17.
 */
public class Login {

    public static Form<Login> loginForm = Form.form(Login.class);

    private String email;

    @Constraints.Required
    private String password;

    @Constraints.Required
    private String username;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

