package services;

import javafx.scene.control.Tab;
import models.*;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lapa on 6/14/17.
 */
public class AdminService implements BaseService {

    private RestaurantService restaurantService;
    private LocationService locationService;
    private UserService userService;
    private CategoryService categoryService;
    private TableService tableService;

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

    @Inject
    public void setTableService(TableService tableService) {
        this.tableService = tableService;
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

    public AppUser addUser(AppUser user) {
        return userService.saveUser(user);
    }

    public AppUser editUser(AppUser user) {
        return userService.updateUser(user);
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    public AppUser getUserDetails(Long id){
        return userService.getUserDetails(id);
    }

    public List getAllTables(Restaurant restaurant) {
        return tableService.getAllTables(restaurant);
    }

    public RestaurantTable addTable(RestaurantTable table){
        return tableService.saveTable(table);
    }

    public RestaurantTable editTable(RestaurantTable table) {
        return tableService.updateTable(table);
    }

    public void deleteTable(Long id) {
        tableService.deleteRestaurantTable(id);
    }
}
