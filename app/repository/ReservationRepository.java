package repository;

import models.Reservation;
import models.Restaurant;
import models.RestaurantTable;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import play.db.jpa.JPA;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by lapa on 5/25/17.
 */
public class ReservationRepository extends RepositoryImplementation<Reservation> {


    public Restaurant findReservatedRestaurantById(Long id) {

        return JPA.em().find(Restaurant.class, id);
    }

    public List getAllReservationsOnSelectedDate(String date, List tables) {

        return getCriteria(Reservation.class)
                .add(Restrictions.eq("date", date))
                .add(Restrictions.in("table", tables))
                .addOrder( Order.asc("reservationsStart") ).list();
    }

    public List getTablesForSelectedNumberOfPeople(Long restaurantId, int numberOfPeople) {

        return getCriteria(RestaurantTable.class)
                .add(Restrictions.eq("restaurantId", restaurantId))
                .add(Restrictions.ge("numberOfChairs", numberOfPeople)).list();

    }


}
