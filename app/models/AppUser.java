package models;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.HibernateEntityManager;
import play.Logger;
import play.data.Form;
import play.db.jpa.JPA;

import javax.persistence.*;
import javax.persistence.Entity;


/**
 * Created by lapa on 4/17/17.
 */

@Entity
public class AppUser {

    public static Form<AppUser> userForm = Form.form(AppUser.class);

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true)
    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private String country;

    private String city;

    private String password;

    private String confirmPassword;

    private String token;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static Session getSession(){
        Session session = ((HibernateEntityManager) JPA.em()).getSession();
        return session;
    }


    public static Criteria getCriteria(){
            Session session = getSession();
        return session.createCriteria(AppUser.class);
    }



    public static AppUser findUserByEmail(String email) {
        AppUser user = (AppUser) getCriteria().add(Restrictions.eq("email", email)).uniqueResult();
        return user;
    }


//    public static AppUser saveUser(AppUser user){
//        getSession().persist(user);
//        getSession().flush();
//        return user;
//    }

    public static AppUser authenticate(String email, String password) {

        AppUser user = (AppUser) getCriteria().add(Restrictions.eq("email", email)).uniqueResult();

        if(user != null && user.getPassword().equals(password)){

            return user;

        } else {

            return null;
        }

    }




}



