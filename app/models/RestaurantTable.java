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
    private Integer id;

    @Column(name="restaurant_id")
    private Integer restaurantId;

    @Column(name="num_of_chairs")
    private Integer numberOfChairs;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getNumberOfChairs() {
        return numberOfChairs;
    }

    public void setNumberOfChairs(Integer numberOfChairs) {
        this.numberOfChairs = numberOfChairs;
    }


    public static Session getSession(){
        Session session = ((HibernateEntityManager) JPA.em()).getSession();
        return session;
    }


    public static Criteria getCriteria(){
        Session session = getSession();
        return session.createCriteria(RestaurantTable.class);
    }

    public static List<RestaurantTable> getTables(int restaurantId, int numberOfPeople) {
       List<RestaurantTable> tables = getCriteria().add(Restrictions.eq("restaurantId", restaurantId))
               .add(Restrictions.ge("numberOfChairs", numberOfPeople)).list();
       return tables;
    }
}
