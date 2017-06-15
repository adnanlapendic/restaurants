package repository;

import models.Restaurant;
import models.RestaurantTable;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by lapa on 5/25/17.
 */
public class TableRepository extends RepositoryImplementation<RestaurantTable> {

    public List getTablesForSelectedNumberOfPeople(Long restaurantId, int numberOfPeople) {

        return getCriteria()
                .add(Restrictions.eq("restaurantId", restaurantId))
                .add(Restrictions.ge("numberOfChairs", numberOfPeople)).list();

    }

    public List getAllRestaurantTable(Restaurant restaurant) {
        return getCriteria()
                .add(Restrictions.eq("restaurantId", restaurant.getId())).list();
    }

}
