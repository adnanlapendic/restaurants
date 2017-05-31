package controllers;

import models.Reservation;
import models.ReservationResponse;
import models.Response;
import models.Restaurant;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ReservationService;
import services.TableService;

import javax.inject.Inject;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lapa on 5/7/17.
 */
public class ReservationController extends Controller {

    private ReservationService reservationService;

    @Inject
    public void setReservationService(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @Transactional(readOnly = true)
    public Result checkForTable() {

        Form<Reservation> boundForm = Reservation.reservationForm.bindFromRequest();

    Long restaurantId = Long.valueOf(boundForm.bindFromRequest().field("restaurantId").value());
    Restaurant restaurant = reservationService.getRestaurantById(restaurantId);
    String restaurantName = restaurant.getName();
    String restaurantImage = restaurant.getImage();
    int people = boundForm.get().getNumberOfPeople();
    int numOfFreeTables = reservationService.getTablesForSelectedNumberOfPeople(restaurantId, people).size();
    String date = boundForm.get().getDate();
    String time = boundForm.get().getTime();
        Timestamp timestamp = Timestamp.valueOf(date + " " + time + ":00.0");
        Long timestamp2 = timestamp.getTime();
        if(timestamp2 < System.currentTimeMillis()){
            return badRequest(Response.errorResponse("Can't make reservation for past time."));
        }

        List<String> bestTimes = reservationService.getBestTimes(date, timestamp2, restaurantId, people);

        ReservationResponse reservationResponse = new ReservationResponse(numOfFreeTables,bestTimes,restaurantId,restaurantName, restaurantImage,timestamp,people,time);

        return ok(Json.toJson(reservationResponse));

    }

}
