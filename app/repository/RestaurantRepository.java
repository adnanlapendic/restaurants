package repository;

import com.google.inject.ImplementedBy;
import com.google.inject.Inject;
import models.Restaurant;
import models.RestaurantTable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.jpa.HibernateEntityManager;
import play.db.jpa.JPA;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lapa on 5/6/17.
 */
public class RestaurantRepository extends RepositoryImplementation<Restaurant> {

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

}
