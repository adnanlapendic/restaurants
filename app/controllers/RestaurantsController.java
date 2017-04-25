package controllers;

import models.Restaurant;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import play.Logger;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lapa on 4/11/17.
 */
public class RestaurantsController extends Controller {

    @Transactional()
    public Result getAllRestaurants(){
        List<Restaurant> restaurants = Restaurant.getRestaurants();
        return ok(Json.toJson(restaurants));
    }


    @Transactional
    public Result getPopularLocations () {

     //TODO - return all cities an number of columns for each city

//        Criteria crit = Restaurant.getCriteria();
//        ProjectionList projList = Projections.projectionList();
//        projList.add(Projections.count("city"));
////        projList.add(Projections.property("city"));
//        crit.setProjection(projList);
//
//
//        Map<String, Integer> map = new HashMap<>();
//        List<Restaurant> restaurants = Restaurant.getRestaurants();
//
//        for (int i = 0; i < restaurants.size(); i++) {
//
//            String key = restaurants.get(i).getCity();
//
//            Integer value = map.get(key);
//
//            if(value == null){
//                value = 0;
//            }
//
//                map.put(key, value + 1);
//        }

        return ok(Json.toJson("map"));
    }

    @Transactional
    public Result getRestaurantDetails(){
        Logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        int restaurantId = Restaurant.restaurantForm.bindFromRequest().get().getId();

        Restaurant restaurant = Restaurant.getRestaurantById(restaurantId);

        if(restaurant != null) {
            return ok(Json.toJson(restaurant));
        }else {
            return badRequest();
        }
    }

}
