package repository;

import com.google.inject.ImplementedBy;
import models.Restaurant;
import models.RestaurantTable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.HibernateEntityManager;
import play.db.jpa.JPA;

import java.util.List;

/**
 * Created by lapa on 5/6/17.
 */
//@ImplementedBy(RestaurantRepositoryImplementation.class)
public class RestaurantRepository extends RepositoryImplementation<Restaurant> {

        public static Criteria getCriteria(){

        Session session = ((HibernateEntityManager) JPA.em()).getSession();
        return session.createCriteria(Restaurant.class);
    }


    public Restaurant findRestaurantById(int id) {
        Restaurant restaurant = (Restaurant) getCriteria().add(Restrictions.eq("id", id)).uniqueResult();

        return restaurant;
    }

    public List<RestaurantTable> getRestaurantTables(Restaurant restaurant) {

        List<RestaurantTable> tables =  getCriteria().add(Restrictions.eq("restaurantId", restaurant.getId())).list();

        return tables;
    }

}
