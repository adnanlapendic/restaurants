package services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import models.Restaurant;
import models.RestaurantTable;
import repository.TableRepository;

import java.util.List;

/**
 * Created by lapa on 5/25/17.
 */
@Singleton
public class TableService implements BaseService {

    private TableRepository tableRepository;

    @Inject
    public void setTableRepository(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    public List getTablesForSelectedPeople(Long restaurantId, int numOfPople) {
        return tableRepository.getTablesForSelectedNumberOfPeople(restaurantId, numOfPople);
    }

    public RestaurantTable findTableById(Long id) {
        return tableRepository.findById(id);
    }

}
