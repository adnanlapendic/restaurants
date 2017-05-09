package controllers;

import models.Reservation;
import models.ReservationResponse;
import models.Restaurant;
import models.RestaurantTable;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lapa on 5/7/17.
 */
public class ReservationController extends Controller {

    @Transactional(readOnly = true)
    public Result checkForTable() {
        Form<ReservationResponse> boundForm = ReservationResponse.reservationResponseForm.bindFromRequest();

    int restaurantId = boundForm.get().getRestaurantId();

    Restaurant restaurant = Restaurant.getRestaurantById(restaurantId);

    String restaurantName = restaurant.getName();

    String restaurantImage = restaurant.getImage();

    Date date = boundForm.get().getDate();

    String time = boundForm.get().getTime();

    int people = boundForm.get().getPeople();

    int numOfFreeTables = RestaurantTable.getTables(restaurantId, people).size();


        if(restaurantName == null){
                Logger.info("987987987987987987987");
            int restaurantId1 = 5;
            String restaurantName1 = "L A P A";
            String restaurantImage1 = "http://ceka.ba/images/ceka1-b.jpg";
            Date date1 = new Date();
            String time1 = "02:14 PM";
            int people1 = 7;
            int numOfFreeTables1 = 22;
            List<String> bestTimes1 = new ArrayList<>();
            bestTimes1.add(time.toString());
            bestTimes1.add("10:30 AM");
            bestTimes1.add("11:00 AM");
            bestTimes1.add("11:30 AM");
            ReservationResponse reservationResponse1 = new ReservationResponse(numOfFreeTables1,bestTimes1,restaurantId1,restaurantName1, restaurantImage1,date1,people1,time1);

            return ok(Json.toJson(reservationResponse1));
                }




        List<String> bestTimes = new ArrayList<>();
        bestTimes.add(time.toString());
        bestTimes.add("10:30 AM");
        bestTimes.add("11:00 AM");
        bestTimes.add("11:30 AM");

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy ");
        String d = sdf.format(date);

        ReservationResponse reservationResponse = new ReservationResponse(numOfFreeTables,bestTimes,restaurantId,restaurantName, restaurantImage,date,people,time);


        return ok(Json.toJson(reservationResponse));
    }

}
