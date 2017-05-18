package models;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.HibernateEntityManager;
import play.Logger;
import play.data.Form;
import play.db.jpa.JPA;
import play.libs.Json;
import play.mvc.Result;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lapa on 4/11/17.
 */

@Entity
public class Restaurant {

    public static Form<Restaurant> restaurantForm = Form.form(Restaurant.class);


    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="city")
    private String city;

    @Column(name="address")
    private String address;

    @Column(name="cousine")
    private String cousine;

    @Column(name="image")
    private String image;

    @Column(name="coverimage")
    private String coverImage;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

    @OneToMany(mappedBy = "restaurantId")
    private List<RestaurantTable> tables;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public List<RestaurantTable> getTables() {
        return tables;
    }

    public void setTables(List<RestaurantTable> tables) {
        this.tables = tables;
    }


}

