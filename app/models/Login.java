package models;

import play.data.Form;

/**
 * Created by lapa on 5/18/17.
 */
public class Login {

    public static Form<Login> loginForm = Form.form(Login.class);

    private String email;

    private String password;

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

