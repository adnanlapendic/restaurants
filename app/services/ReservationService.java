package services;

import models.*;
import play.Logger;
import repository.ReservationRepository;
import repository.RestaurantRepository;
import repository.TableRepository;
import scala.App;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Created by lapa on 5/25/17.
 */
@Singleton
public class ReservationService implements BaseService {

    private final Long HALF_HOUR = 1800000L;
    private final Long HOUR = 3600000L;
    private final Long RESERVATION_PERIOD = 7200000L;


    private ReservationRepository reservationRepository;
    private RestaurantService restaurantService;
    private TableService tableService;
    private UserService userService;

    @Inject
    public void setReservationRepository(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Inject
    public void setRestaurantService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Inject
    public void setTableService(TableService tableService) {
        this.tableService = tableService;
    }

    @Inject
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Restaurant getRestaurantById(Long id) {
        return restaurantService.getRestaurantById(id);
    }


    public List getTablesForSelectedNumberOfPeople(Long id, int numbOfPeople) {
        return tableService.getTablesForSelectedPeople(id, numbOfPeople);
    }


    private boolean hasFreeTables(List<Reservation> reservations, Long selectedTimeAndDate) {

        boolean bool = false;
        for(Reservation reservation : reservations) {
            if ((!Objects.equals(reservation.getReservationsStart(), selectedTimeAndDate))
                    && (reservation.getReservationsStart() != selectedTimeAndDate + HALF_HOUR)
                    && (reservation.getReservationsStart() != selectedTimeAndDate + HOUR)
                    && (reservation.getReservationsStart() != selectedTimeAndDate + (HOUR + HALF_HOUR))
                    && (reservation.getReservationsStart() != selectedTimeAndDate - HALF_HOUR)
                    && (reservation.getReservationsStart() != selectedTimeAndDate - HOUR)
                    && (reservation.getReservationsStart() != selectedTimeAndDate - (HOUR + HALF_HOUR))) {
                bool = true;
            }


//            if((reservation.getReservationEnds() <= selectedTimeAndDate)) {
//                    bool = true;
//
//            }

        }
        return bool;

    }


    public List getBestTimes(String date, Long dateAndTime, Long restaurantId, int people) {

        Timestamp t1 = Timestamp.valueOf(date + " 08:00:00.0");
        Timestamp t2 = Timestamp.valueOf(date + " 21:00:00.0");
        Long minTime = t1.getTime();
        Long maxTime = t2.getTime();
        ArrayList<String> bestTime = new ArrayList<>();
        List<RestaurantTable> tables = tableService.getTablesForSelectedPeople(restaurantId, people);
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

            timeToCheckUp = stepUp(timeToCheckUp, maxTime);
            timeToCheckDown = stepDown(timeToCheckDown, minTime);

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


    private Long stepUp(Long time, Long maxTime) {
        if (time != null) {
            if (time <= maxTime) {
                return time + HALF_HOUR;
            }
        }
        return null;

    }


    private Long stepDown(Long time, Long minTime) {
        if(time != null) {
            if (time > minTime) {
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

    public ReservationResponse getReservation(Restaurant restaurant, Reservation reservation) {

        int people = reservation.getNumberOfPeople();
        int numOfFreeTables = getTablesForSelectedNumberOfPeople(restaurant.getId(), people).size();
        String date = reservation.getDate();
        String time = reservation.getTime();
        Timestamp timestamp = Timestamp.valueOf(date + " " + time + ":00.0");
        Long timestamp2 = timestamp.getTime();

        List<String> bestTimes = getBestTimes(date, timestamp2, restaurant.getId(), people);

        ReservationResponse reservationResponse = new ReservationResponse(numOfFreeTables, bestTimes, timestamp, people, time, restaurant);

        return reservationResponse;
    }

    public Reservation saveNewReservation(Restaurant restaurant, Reservation reservation){
        Reservation reservation1 = new Reservation();
        Long reservationTime = Timestamp.valueOf(reservation.getDate() + " " + reservation.getTime() + ":00.0").getTime();

        reservation1.setRestaurantTable(getFreeTable(restaurant.getId(), reservationTime, reservation.getNumberOfPeople()));
        reservation1.setRestaurant(restaurant);
        reservation1.setDate(reservation.getDate());
        reservation1.setUser(userService.getCurrentUser());
        reservation1.setTime(reservation.getTime());
        reservation1.setNumberOfPeople(reservation.getNumberOfPeople());
        reservation1.setReservationsStart(reservationTime);
        reservation1.setReservationEnds(reservationTime + RESERVATION_PERIOD);
        reservationRepository.create(reservation1);

        return reservation1;
    }


    public RestaurantTable getFreeTable(Long restaurantId, Long dateAndTime, int numOfPeople) {

        List<RestaurantTable> tables = tableService.getTablesForSelectedPeople(restaurantId, numOfPeople);

        for(RestaurantTable table : tables) {
            List freeTables = reservationRepository.getFreeTables(dateAndTime, table);

            if(freeTables.size() < tables.size()){
                return tableService.findTableById(table.getId());
            }

        }

       return null;
    }

    public ReservationResponseTwo getResponse(Reservation reservation) {

        return new ReservationResponseTwo(reservation.getId(), reservation.getUser().getId(), reservation.getRestaurantTable().getId(), reservation.getReservationsStart(), reservation.getNumberOfPeople());


    }

    public List getReservationsForUser() {
        AppUser user = userService.getCurrentUser();
        return reservationRepository.getAllReservationsForUser(user);
    }

    public List getAllRestaurants() {
        return restaurantService.getRestaurants();
    }

    public List getAllRestaurantsWithFreeTables(List<Restaurant> restaurants, Reservation reservation) {
        List<ReservationResponse> listOfRestaurants = new ArrayList<>();
        for(Restaurant restaurant : restaurants) {
            listOfRestaurants.add(getReservation(restaurant, reservation));
        }
        return listOfRestaurants;
    }

}
