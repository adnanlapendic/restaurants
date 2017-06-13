package repository;

import models.AppUser;
import models.Reservation;
import models.Restaurant;
import models.RestaurantTable;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import play.Logger;
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
                .add(Restrictions.in("table", tables)).list();
    }

    public List getFreeTables(Long dateAndTime, RestaurantTable table){

        List res = getCriteria()
                .add(Restrictions.eq("reservations_start", dateAndTime))
                .add(Restrictions.eq("table", table)).list();
        return res;
    }

    public List getAllReservationsForUser(AppUser user) {
        return getCriteria()
                .add(Restrictions.eq("user", user)).list();
    }

}
