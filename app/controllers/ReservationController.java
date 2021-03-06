package controllers;

import models.*;
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
    public Result checkForTable(Long restaurantId) {

        Form<Reservation> boundForm = Reservation.reservationForm.bindFromRequest();

        Restaurant restaurant = reservationService.getRestaurantById(restaurantId);
        Reservation reservation = boundForm.get();

        ReservationResponse reservationResponse = reservationService.getReservation(restaurant, reservation);
        Long timestamp = reservationResponse.getDate().getTime();

        if (timestamp < System.currentTimeMillis()) {
            return badRequest(Response.errorResponse("Can't make reservation for past time."));
        }

        return ok(Json.toJson(reservationResponse));

    }

    @Transactional
    public Result makeReservation(Long restaurantId) {

        Form<Reservation> boundForm = Reservation.reservationForm.bindFromRequest();
        Restaurant restaurant = reservationService.getRestaurantById(restaurantId);
        Reservation reservation = boundForm.get();

        if (boundForm.hasErrors()){
            return badRequest();
        }

        Reservation reservationToSave = reservationService.saveNewReservation(restaurant, reservation);
        ReservationResponseTwo reservationResponse = reservationService.getResponse(reservationToSave);

        return ok(Json.toJson(reservationResponse));

    }

    @Transactional(readOnly = true)
    public Result getListOfReservationsForUser() {
        return ok(Json.toJson(reservationService.getReservationsForUser()));
    }

    @Transactional(readOnly = true)
    public Result getFreeTables() {

        Form<Reservation> boundForm = Reservation.reservationForm.bindFromRequest();

        List restaurants = reservationService.getAllRestaurants();
        Reservation reservation = boundForm.get();

        List listOfRestaurantsWithFreeTables = reservationService.getAllRestaurantsWithFreeTables(restaurants, reservation);

        return ok(Json.toJson(listOfRestaurantsWithFreeTables));
    }

}
