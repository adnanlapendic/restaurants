package services;

import models.Restaurant;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.jpa.HibernateEntityManager;
import play.db.jpa.JPA;
import repository.RestaurantRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lapa on 5/17/17.
 */
public class RestaurantService implements BaseService{

    private RestaurantRepository restaurantRepository;

    @Inject
    public void setRestaurantRepository(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Criteria getCriteria() {
        Session session = ((HibernateEntityManager) JPA.em()).getSession();

        return session.createCriteria(Restaurant.class);
    }

    public List<Restaurant> getRestaurants(){
        List<Restaurant> restaurants = getCriteria().list();

        return restaurants;
    }

    public List<Object> getRestaurantsPerCity() {

        List<Object[]> obj = JPA.em().createQuery("select count(r.city) as count, r.city from Restaurant as r group BY r.city").getResultList();

        List<Object> oo = new ArrayList<>();
        for (Object[] o : obj) {
            Map<String, String> map = new HashMap<>();
            map.put("city", o[1].toString());
            map.put("count", o[0].toString());
            oo.add(map);
        }

        return oo;
    }

    public Restaurant getRestaurantDetails(int restaurantId) {

        Restaurant restaurant = restaurantRepository.findRestaurantById(restaurantId);

        return  restaurant;
    }

}
