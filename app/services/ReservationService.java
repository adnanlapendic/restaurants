package services;

import models.Reservation;
import models.Restaurant;
import models.RestaurantTable;
import play.Logger;
import repository.ReservationRepository;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lapa on 5/25/17.
 */
public class ReservationService implements BaseService {

    private final Long HALF_HOUR = 1800000L;
    private final Long HOUR = 3600000L;
    private final Long RESERVATION_PERIOD = 7200000L;
    private Long FIRST_RESERVATION = null;
    private Long LAST_RESERVATION = null;


    private ReservationRepository reservationRepository;

    @Inject
    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    public Restaurant getRestaurantById(Long id) {
        return reservationRepository.findReservatedRestaurantById(id);
    }


    public List getTablesForSelectedNumberOfPeople(Long id, int numbOfPeople) {
        return reservationRepository.getTablesForSelectedNumberOfPeople(id, numbOfPeople);
    }

    private boolean hasFreeTables(List<Reservation> reservations, Long selectedTimeAndDate) {
            boolean bool = false;
        for(Reservation reservation : reservations) {
            if ((reservation.getReservationsStart() != selectedTimeAndDate) && (reservation.getReservationsStart() != selectedTimeAndDate + HALF_HOUR)
                    && (reservation.getReservationsStart() != selectedTimeAndDate + HOUR)) {
                bool = true;
            }
        }
        return bool;

    }


    public List getBestTimes(String date, Long dateAndTime, Long restaurantId, int people) {
        Timestamp t1 = Timestamp.valueOf(date + " 08:00:00.0");
        Timestamp t2 = Timestamp.valueOf(date + " 21:00:00.0");
        FIRST_RESERVATION = t1.getTime();
        LAST_RESERVATION = t2.getTime();
        ArrayList<String> bestTime = new ArrayList<>();
        List<RestaurantTable> tables = reservationRepository.getTablesForSelectedNumberOfPeople(restaurantId, people);
        List<Reservation> reservations = reservationRepository.getAllReservationsOnSelectedDate(date, tables);


        if(reservations.size() == 0){
            return getFirstReservation(dateAndTime);
        } else {
            for (RestaurantTable table: tables) {
                if(!reservations.contains(table)){
                    return getFirstReservation(dateAndTime);
                }
            }
        }

        Long timeToCheckUp = dateAndTime;
        Long timeToCheckDown = dateAndTime;
        int loopBreaker = 0;

        while (bestTime.size() < 4) {
            loopBreaker++;
            if (timeToCheckUp != null) {
                if (hasFreeTables(reservations, timeToCheckUp)) {
                    Date date2 = new Date(timeToCheckUp);
                    DateFormat formatter = new SimpleDateFormat("HH:mm");
                    bestTime.add(formatter.format(date2));

                }
            }

            timeToCheckUp = stepUp(timeToCheckUp);
            timeToCheckDown = stepDown(timeToCheckDown);

            if (timeToCheckDown != null) {
                if (hasFreeTables(reservations, timeToCheckDown)) {
                    Date date2 = new Date(timeToCheckDown);
                    DateFormat formatter = new SimpleDateFormat("HH:mm");
                    bestTime.add(formatter.format(date2));

                }
            }

            if(loopBreaker > 30){
                break;

            }
        }

        return bestTime;

    }


    private Long stepUp(Long time) {
        if (time != null) {
            if (time <= LAST_RESERVATION) {
                return time + HALF_HOUR;
            }
        }
        return null;

    }


    private Long stepDown(Long time) {
        if(time != null) {
            if (time > FIRST_RESERVATION) {
                return time - HALF_HOUR;
            }
        }
        return null;
    }


    private List getFirstReservation(Long timestamp) {
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        ArrayList<String> bestTimes = new ArrayList<>();

        bestTimes.add(formatter.format(new Date(timestamp - HALF_HOUR)));
        bestTimes.add(formatter.format(new Date(timestamp)));
        bestTimes.add(formatter.format(new Date(timestamp + HALF_HOUR)));
        bestTimes.add(formatter.format(new Date(timestamp + HOUR)));

        return bestTimes;
    }

}
