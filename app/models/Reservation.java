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
    private int numberOfPeople;

    @Transient
    private String time;

    private String date;

    private Long reservationsStart;

    private Long reservationEnds;

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
        return reservationsStart;
    }

    public void setReservationsStart(Long reservationsStart) {
        this.reservationsStart = reservationsStart;
    }

    public Long getReservationEnds() {
        return reservationEnds;
    }

    public void setReservationEnds(Long reservationEnds) {
        this.reservationEnds = reservationEnds;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
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
