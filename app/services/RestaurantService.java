package services;

import models.Restaurant;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import play.db.jpa.JPA;
import repository.RestaurantRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lapa on 5/17/17.
 */
public class RestaurantService implements BaseService{

    private RestaurantRepository restaurantRepository;

    @Inject
    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    public List<Restaurant> getRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();

        return restaurants;
    }

    public List<Object> getRestaurantsPerCity() {

        return restaurantRepository.getRestaurantsPerCity();
    }

    public Restaurant getRestaurantDetails(Long restaurantId) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        return  restaurant;
    }

}
