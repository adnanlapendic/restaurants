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

   @Transactional
    public Result addUser() {
        Form<AppUser> boundForm = AppUser.userForm.bindFromRequest();
        AppUser user = boundForm.get();
        return ok(Json.toJson(adminService.addUser(user)));
   }

   @Transactional
    public Result editUser() {
       Form<AppUser> boundForm = AppUser.userForm.bindFromRequest();
       AppUser user = boundForm.get();
       return ok(Json.toJson(adminService.editUser(user)));
   }

   @Transactional
    public Result deleteUser() {
       Form<AppUser> boundForm = AppUser.userForm.bindFromRequest();
       Long id = Long.valueOf(boundForm.data().get("id"));
       adminService.deleteUser(id);
       return ok(Json.toJson(Response.succsessResponse("User successfully deleted.")));

   }

   @Transactional(readOnly = true)
    public Result getUserDetails() {
       Form<AppUser> boundForm = AppUser.userForm.bindFromRequest();
       Long id = Long.valueOf(boundForm.data().get("id"));
       return ok(Json.toJson(adminService.getUserDetails(id)));
   }

   @Transactional
    public Result getAllRestaurnatTables() {
       Form<Restaurant> boundForm = Restaurant.restaurantForm.bindFromRequest();
       Restaurant restaurant = boundForm.get();
       return ok(Json.toJson(adminService.getAllTables(restaurant)));
   }

   @Transactional
    public Result addRestaurntTable() {
       Form<RestaurantTable> boundForm = RestaurantTable.tableForm.bindFromRequest();
        RestaurantTable table = boundForm.get();
        return ok(Json.toJson(adminService.addTable(table)));
   }

   @Transactional
    public Result editTable() {
       Form<RestaurantTable> boundForm = RestaurantTable.tableForm.bindFromRequest();
       RestaurantTable table = boundForm.get();
       return ok(Json.toJson(adminService.editTable(table)));
   }

   @Transactional
    public Result deleteTable() {
       Form<RestaurantTable> boundForm = RestaurantTable.tableForm.bindFromRequest();
       Long id = Long.valueOf(boundForm.data().get("id"));
       adminService.deleteTable(id);
       return ok(Json.toJson(Response.succsessResponse("Table successfully deleted.")));
   }

}
