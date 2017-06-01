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
@Singleton
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

    public Restaurant getRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId);
    }

}
