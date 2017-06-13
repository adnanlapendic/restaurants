package models;

import play.data.Form;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by lapa on 5/2/17.
 */

@Entity
public class Reservation {

    public static Form<Reservation> reservationForm = Form.form(Reservation.class);

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="restaurant_id", referencedColumnName = "id")
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private RestaurantTable table;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    @Transient
    private int number_of_people;

    @Transient
    private String time;

    private String date;

    private Long reservations_start;

    private Long reservation_ends;

    private boolean isReserved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RestaurantTable getRestaurantTable() {
        return table;
    }

    public void setRestaurantTable(RestaurantTable table) {
        this.table = table;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Long getReservationsStart() {
        return reservations_start;
    }

    public void setReservationsStart(Long reservationsStart) {
        this.reservations_start = reservationsStart;
    }

    public Long getReservationEnds() {
        return reservation_ends;
    }

    public void setReservationEnds(Long reservationEnds) {
        this.reservation_ends = reservationEnds;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public int getNumberOfPeople() {
        return number_of_people;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.number_of_people = numberOfPeople;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
