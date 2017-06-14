package controllers;

import models.Location;
import models.Reservation;
import models.Response;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.AdminService;
import services.LocationService;

import javax.inject.Inject;

/**
 * Created by lapa on 6/14/17.
 */
public class AdminController extends Controller {

    private AdminService adminService;
    private LocationService locationService;

    @Inject
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Inject
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }

    @Transactional(readOnly = true)
   public Result getAdminCounters() {

       return ok(Json.toJson(adminService.getAdminCounters()));
   }

   @Transactional
    public Result addLocation(){
       Form<Location> boundForm = Location.locationForm.bindFromRequest();
       Location location = boundForm.get();

       locationService.saveNewLocation(location);

       return ok(Json.toJson(location));
   }

   @Transactional
    public Result editLocation() {
       Form<Location> boundForm = Location.locationForm.bindFromRequest();
       Location location = boundForm.get();
       return ok(Json.toJson(locationService.editLocation(location)));
   }

   @Transactional
    public Result deleteLocation() {
       Form<Location> boundForm = Location.locationForm.bindFromRequest();
       Long id = Long.valueOf(boundForm.data().get("id"));
       locationService.deleteLocation(id);

       return ok(Json.toJson(Response.succsessResponse("Location successfully deleted.")));
   }

   @Transactional
    public Result getLocationDetails() {
       Form<Location> boundForm = Location.locationForm.bindFromRequest();
       Long id = Long.valueOf(boundForm.data().get("id"));
       return ok(Json.toJson(locationService.getLocationDetails(id)));

   }
}
