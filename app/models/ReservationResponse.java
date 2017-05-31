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
    private Long restaurantId;
    private String restaurantName;
    private String restaurantImage;
    private Timestamp date;
    private int people;
    private String time;

    public ReservationResponse(int tablesLeft, List<String> bestTime, Long restaurantId, String restaurantName, String restaurantImage, Timestamp date, int people, String time) {
        this.tablesLeft = tablesLeft;
        this.bestTime = bestTime;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantImage = restaurantImage;
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
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
        this.restaurantImage = restaurantImage;
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
