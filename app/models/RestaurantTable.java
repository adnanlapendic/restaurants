package models;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.HibernateEntityManager;
import play.db.jpa.JPA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

/**
 * Created by lapa on 5/1/17.
 */

@Entity
public class RestaurantTable {

    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="restaurant_id")
    private Long restaurantId;

    @Column(name="num_of_chairs")
    private Integer numberOfChairs;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getNumberOfChairs() {
        return numberOfChairs;
    }

    public void setNumberOfChairs(Integer numberOfChairs) {
        this.numberOfChairs = numberOfChairs;
    }

}
