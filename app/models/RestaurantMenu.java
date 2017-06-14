package models;

import play.data.Form;

import javax.persistence.*;

/**
 * Created by lapa on 6/14/17.
 */
@Entity
public class RestaurantMenu {

    public static Form<RestaurantMenu> menuForm = Form.form(RestaurantMenu.class);


    @Id
    @GeneratedValue
    private Long id;

    private Long restaurant_id;
    private String type;
    private String name;
    private int price;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurant() {
        return restaurant_id;
    }

    public void setRestaurant(Long restaurantId) {
        this.restaurant_id = restaurantId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
