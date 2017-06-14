package services;

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

    public Map getAdminCounters() {
        Map result = new HashMap();
        result.put("restaurantsNumber", restaurantService.getRestaurants().size());
        result.put("LocationsNumber", locationService.getAllLocations().size());
        result.put("usersNumber", userService.getAllUsers().size());
        return result;
    }
}
