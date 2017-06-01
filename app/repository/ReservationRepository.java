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


    public List getAllReservationsOnSelectedDate(String date, List tables) {

        return getCriteria()
                .add(Restrictions.eq("date", date))
                .add(Restrictions.in("table", tables))
                .addOrder( Order.asc("reservationsStart") ).list();
    }


}
