package repository;

import com.google.inject.ImplementedBy;
import models.Restaurant;

/**
 * Created by lapa on 5/6/17.
 */
@ImplementedBy(RestaurantRepositoryImplementation.class)
public interface RestaurantRepository extends Repository<Restaurant> {
}
