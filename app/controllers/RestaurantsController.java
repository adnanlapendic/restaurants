package controllers;

import models.Restaurant;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.HibernateEntityManager;
import org.hibernate.transform.Transformers;
import play.Logger;
import play.db.jpa.JPA;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
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



    @Transactional(readOnly = true)
    public Result getNumberOfRestaurantsPerCity () {


        List<Object[]> obj = JPA.em().createQuery("select count(r.city) as count, r.city from Restaurant as r group BY r.city").getResultList();


        List<Object> oo = new ArrayList<>();
        for (Object[] o : obj) {
            Map<String, String> map = new HashMap<>();
            map.put("city", o[1].toString());
            map.put("count", o[0].toString());
            oo.add(map);
        }

        return ok(Json.toJson(oo));
    }

    @Transactional
    public Result getRestaurantDetails(){
        int restaurantId = Restaurant.restaurantForm.bindFromRequest().get().getId();

        Restaurant restaurant = Restaurant.getRestaurantById(restaurantId);

        if(restaurant != null) {
            return ok(Json.toJson(restaurant));
        }else {
            return badRequest();
        }
    }

    @Transactional
    public Result makeReservation(){


        return ok();
    }

}
