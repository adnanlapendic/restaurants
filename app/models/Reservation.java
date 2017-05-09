package models;

import play.data.Form;

import javax.persistence.*;

/**
 * Created by lapa on 5/2/17.
 */

@Entity
public class Reservation {

    public static Form<Reservation> restaurantForm = Form.form(Reservation.class);


    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "table_id", referencedColumnName = "id")
    private RestaurantTable table;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private AppUser user;

    @Column(name = "time_of_reservation")
    private String timeOfReservation;

    @Column(name = "date_of_reservation")
    private String dateOfReservation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public RestaurantTable getRestaurant() {
        return table;
    }

    public void setRestaurant(RestaurantTable table) {
        this.table = table;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getTimeOfReservation() {
        return timeOfReservation;
    }

    public void setTimeOfReservation(String timeOfReservation) {
        this.timeOfReservation = timeOfReservation;
    }

    public String getDateOfReservation() {
        return dateOfReservation;
    }

    public void setDateOfReservation(String dateOfReservation) {
        this.dateOfReservation = dateOfReservation;
    }
}
