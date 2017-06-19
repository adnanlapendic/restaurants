package models;

import play.data.Form;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by lapa on 5/8/17.
 */
public class ReservationResponse {

    public static Form<ReservationResponse> reservationResponseForm = Form.form(ReservationResponse.class);


    private int tablesLeft;
    private List<String> bestTime;
    private Restaurant restaurant;
    private Timestamp date;
    private int people;
    private String time;

    public ReservationResponse(int tablesLeft, List<String> bestTime, Timestamp date, int people, String time, Restaurant restaurant) {
        this.tablesLeft = tablesLeft;
        this.bestTime = bestTime;
        this.restaurant = restaurant;
        this.date = date;
        this.people = people;
        this.time = time;
    }


    public ReservationResponse() {

    }

    public int getTablesLeft() {
        return tablesLeft;
    }

    public void setTablesLeft(int tablesLeft) {
        this.tablesLeft = tablesLeft;
    }

    public List<String> getBestTime() {
        return bestTime;
    }

    public void setBestTime(List<String> bestTime) {
        this.bestTime = bestTime;
    }

    public Long getRestaurantId() {
        return restaurant.getId();
    }

    public void setRestaurantId(Long restaurantId) {
       this.restaurant.setId(restaurantId);
    }

    public String getRestaurantName() {
        return restaurant.getName();
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurant.setName(restaurantName);
    }

    public String getRestaurantImage() {
        return restaurant.getImage();
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurant.setImage(restaurantImage);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
