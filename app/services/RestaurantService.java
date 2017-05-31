package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Restaurant;
import play.Logger;
import repository.RestaurantRepository;

import java.util.List;

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
        List<Restaurant> restaurants = restaurantRepository.findAll(Restaurant.class);

        return restaurants;
    }

    public List<Object> getRestaurantsPerCity() {

        return restaurantRepository.getRestaurantsPerCity();
    }

    public Restaurant getRestaurantById(Long restaurantId) {
        Logger.info("11111111111111111111111111111111" + restaurantId);
        return restaurantRepository.findById(restaurantId);
    }

}
