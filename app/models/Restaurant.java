package models;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import play.db.jpa.JPA;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lapa on 4/11/17.
 */
@Entity
public class Restaurant {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    private String description;

    private String address;

    private String cousine;

    private String image;

    private String coverImage;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCousine() {
        return cousine;
    }

    public void setCousine(String cousine) {
        this.cousine = cousine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }


    public static Criteria getCriteria(){

        Session session = ((HibernateEntityManager) JPA.em()).getSession();
        return session.createCriteria(Restaurant.class);
    }

    public static List getRestaurants(){
        List<Restaurant> restaurants = getCriteria().list();

        return restaurants;
    }

}

