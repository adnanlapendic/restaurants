package controllers;

import models.RestaurantMenu;
import models.Response;
import models.Restaurant;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.MenuService;
import services.RestaurantService;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by lapa on 4/11/17.
 */
public class RestaurantsController extends Controller {

    private RestaurantService restaurantService;
    private MenuService menuService;

    @Inject
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Inject
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }


    @Transactional()
    public Result getAllRestaurants(){

        return ok(Json.toJson(restaurantService.getRestaurants()));
    }


    @Transactional(readOnly = true)
    public Result getNumberOfRestaurantsPerCity () {

        return ok(Json.toJson(restaurantService.getRestaurantsPerCity()));
    }

    @Transactional
    public Result getRestaurantDetails(){

        Form<Restaurant> boundForm = Restaurant.restaurantForm.bindFromRequest();

        Restaurant restaurant = restaurantService.getRestaurantById(boundForm.get().getId());

        if(restaurant != null) {
            return ok(Json.toJson(restaurant));
        }else {
            return badRequest(Response.errorResponse("Can't load selected restaurant."));
        }
    }

    @Transactional(readOnly = true)
    public Result getAllCategories() {
        List categories = restaurantService.getAllCategories();
        return ok(Json.toJson(categories));
    }

    @Transactional
    public Result getRestaurantMenu() {
        Form<RestaurantMenu> boundForm = RestaurantMenu.menuForm.bindFromRequest();
        String type = boundForm.data().get("type");
        Long restaurantId = Long.valueOf(boundForm.data().get("restaurant"));

        List menuList = menuService.getRestaurantMenu(restaurantId, type);
        return ok(Json.toJson(menuList));
    }

}
