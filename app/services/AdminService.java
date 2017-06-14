package services;

import models.Category;
import models.Location;
import models.Restaurant;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lapa on 6/14/17.
 */
public class AdminService implements BaseService {

    private RestaurantService restaurantService;
    private LocationService locationService;
    private UserService userService;
    private CategoryService categoryService;

    @Inject
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Inject
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Inject
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public Map getAdminCounters() {
        Map result = new HashMap();
        result.put("restaurantsNumber", restaurantService.getRestaurants().size());
        result.put("LocationsNumber", locationService.getAllLocations().size());
        result.put("usersNumber", userService.getAllUsers().size());
        return result;
    }

    public Location saveNewLocation(Location location) {
        return locationService.saveNewLocation(location);

    }

    public Location editLocation(Location location) {
        return locationService.editLocation(location);
    }

    public void deleteLocation(Long id) {
        locationService.deleteLocation(id);
    }

    public Location getLocationDetails(Long id){
        return locationService.getLocationDetails(id);
    }

    public Category saveCategory(Category category) {
        return categoryService.saveCategory(category);

    }

    public Category updateCategory(Category category) {
        return categoryService.updateCategory(category);
    }

    public void deleteCategory(Long id) {
        categoryService.deleteCategory(id);
    }

    public Category getCategoryDetails(Long id){
        return categoryService.getCategoryDetails(id);
    }

    public Restaurant addNewRestaurant(Restaurant restaurant) {
        return restaurantService.saveRestaurant(restaurant);
    }

    public Restaurant editRestaurant(Restaurant restaurant) {
        return restaurantService.updateRestaurant(restaurant);
    }

    public void deleteRestaurant(Long id) {
        restaurantService.deleteRestaurant(id);
    }
}
