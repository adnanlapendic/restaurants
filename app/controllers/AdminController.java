package controllers;

import models.*;
import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.AdminService;
import services.CategoryService;
import services.LocationService;

import javax.inject.Inject;

/**
 * Created by lapa on 6/14/17.
 */
public class AdminController extends Controller {

    private AdminService adminService;

    @Inject
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Transactional(readOnly = true)
   public Result getAdminCounters() {
       return ok(Json.toJson(adminService.getAdminCounters()));
   }

   @Transactional
    public Result addLocation(){
       Form<Location> boundForm = Location.locationForm.bindFromRequest();
       Location location = boundForm.get();
       adminService.saveNewLocation(location);
       return ok(Json.toJson(location));
   }

   @Transactional
    public Result editLocation() {
       Form<Location> boundForm = Location.locationForm.bindFromRequest();
       Location location = boundForm.get();
       return ok(Json.toJson(adminService.editLocation(location)));
   }

   @Transactional
    public Result deleteLocation() {
       Form<Location> boundForm = Location.locationForm.bindFromRequest();
       Long id = Long.valueOf(boundForm.data().get("id"));
       adminService.deleteLocation(id);

       return ok(Json.toJson(Response.succsessResponse("Location successfully deleted.")));
   }

   @Transactional
    public Result getLocationDetails() {
       Form<Location> boundForm = Location.locationForm.bindFromRequest();
       Long id = Long.valueOf(boundForm.data().get("id"));
       return ok(Json.toJson(adminService.getLocationDetails(id)));

   }

   @Transactional
    public Result addCategory() {
       Form<Category> boundForm = Category.categoryForm.bindFromRequest();
       Category category = boundForm.get();
       return ok(Json.toJson(adminService.saveCategory(category)));
   }

   @Transactional
    public Result editCategory() {
       Form<Category> boundForm = Category.categoryForm.bindFromRequest();
       Category category = boundForm.get();
       return ok(Json.toJson(adminService.updateCategory(category)));
   }

   @Transactional
    public Result deleteCategory() {
       Form<Category> boundForm = Category.categoryForm.bindFromRequest();
       Long id = Long.valueOf(boundForm.data().get("id"));
       adminService.deleteCategory(id);
       return ok(Json.toJson(Response.succsessResponse("Category successfully deleted.")));
   }

   @Transactional(readOnly = true)
    public Result getCategoryDetails(){
       Form<Category> boundForm = Category.categoryForm.bindFromRequest();
       Long id = Long.valueOf(boundForm.data().get("id"));
       Category category = adminService.getCategoryDetails(id);
       return ok(Json.toJson(category));
   }

   @Transactional
    public Result addRestaurant() {
        Form<Restaurant> boundForm = Restaurant.restaurantForm.bindFromRequest();
        Restaurant restaurant = boundForm.get();
        return ok(Json.toJson(adminService.addNewRestaurant(restaurant)));
   }

   @Transactional
    public Result editRestaurant() {
       Form<Restaurant> boundForm = Restaurant.restaurantForm.bindFromRequest();
       Restaurant restaurant = boundForm.get();
       return ok(Json.toJson(adminService.editRestaurant(restaurant)));
   }

   @Transactional
    public Result deleteRestaurant() {
       Form<Restaurant> boundForm = Restaurant.restaurantForm.bindFromRequest();
       Long id = Long.valueOf(boundForm.data().get("id"));
       adminService.deleteRestaurant(id);
       return ok(Json.toJson(Response.succsessResponse("Restaurant successfully deleted.")));
   }

}
