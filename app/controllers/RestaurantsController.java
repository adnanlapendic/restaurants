package controllers;

import models.Response;
import models.Restaurant;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.RestaurantService;

import javax.inject.Inject;

/**
 * Created by lapa on 4/11/17.
 */
public class RestaurantsController extends Controller {

    private RestaurantService restaurantService;

    @Inject
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
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

    @Transactional
    public Result makeReservation(){


        return ok();
    }

}
