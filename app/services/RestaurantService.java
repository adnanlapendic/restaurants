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
    private CategoryService categoryService;

    @Inject
    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Inject
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
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

    public List getAllCategories() {
        return categoryService.getAllCategories();
    }

    public Restaurant rateRestaurantByPrice(Restaurant restaurant, int vote) {
        restaurant.setPriceRange(restaurant.getPriceRange() + vote);
        restaurant.setPriceVotes(restaurant.getPriceVotes()+ 1 );
        return restaurantRepository.update(restaurant);
    }
    public Restaurant rateRestaurantWithStars(Restaurant restaurant, int vote) {
        restaurant.setStarRate(restaurant.getStarRate() + vote);
        restaurant.setStarVotes(restaurant.getStarVotes()+ 1 );
        return restaurantRepository.update(restaurant);
    }
}
