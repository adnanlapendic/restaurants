package repository;

import models.RestaurantTable;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by lapa on 5/25/17.
 */
public class TableRepository extends RepositoryImplementation<RestaurantTable> {

    public List<RestaurantTable> getTables(Long restaurantId, int numberOfPeople) {
        List<RestaurantTable> tables = getCriteria(RestaurantTable.class).add(Restrictions.eq("restaurantId", restaurantId))
                .add(Restrictions.ge("numberOfChairs", numberOfPeople))
                .add(Restrictions.ge("isReserved", false)).list();
        return tables;
    }


}
