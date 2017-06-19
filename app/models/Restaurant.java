package models;


import com.google.inject.Inject;
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
    private Long id;

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

    private int price_votes;

    private int price_range;

    private int star_votes;

    private int star_rate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getPriceVotes() {
        return price_votes;
    }

    public void setPriceVotes(int priceVotes) {
        this.price_votes = priceVotes;
    }

    public int getPriceRange() {
        return price_range;
    }

    public void setPriceRange(int priceRange) {
        this.price_range = priceRange;
    }

    public int getStarVotes() {
        return star_votes;
    }

    public void setStarVotes(int starVotes) {
        this.star_votes = starVotes;
    }

    public int getStarRate() {
        return star_rate;
    }

    public void setStarRate(int starRate) {
        this.star_rate = starRate;
    }
}

