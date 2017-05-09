import Ember from 'ember';

export default Ember.Route.extend({

  restaurantService: Ember.inject.service('restaurant-service'),
  userService: Ember.inject.service('user-service'),

  model(params) {
    return Ember.RSVP.hash({
      restaurant: this.get('restaurantService').getSingleRestaurant(params.id),
      // reservationResponse: this.get('restaurantService').checkReservationAvailability(),
    });
  },


});
