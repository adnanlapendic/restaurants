package controllers;

import models.Restaurant;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * Created by lapa on 4/11/17.
 */
public class RestaurantsController extends Controller {

    @Transactional()
    public Result getAllRestaurants(){
        List<Restaurant> restaurants = Restaurant.getRestaurants();
        return ok(Json.toJson(restaurants));
    }

}
