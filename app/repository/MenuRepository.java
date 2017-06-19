package repository;

import models.RestaurantMenu;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by lapa on 6/14/17.
 */
public class MenuRepository extends RepositoryImplementation<RestaurantMenu> {

    public List getMenuForSelectedRestaurant(Long restaurantId, String type) {

        return getCriteria()
                .add(Restrictions.eq("restaurant_id", restaurantId))
                .add(Restrictions.eq("type", type)).list();

    }

}
