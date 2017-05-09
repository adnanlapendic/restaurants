import Ember from 'ember';

export default Ember.Route.extend({

  restaurantService: Ember.inject.service('restaurant-service'),
  userService: Ember.inject.service('user-service'),

  model() {
    return Ember.RSVP.hash({
      restaurants: this.get('restaurantService').getAllRestaurants(),
      user: this.get('userService').getCurrentUser(),
      locations: this.get('restaurantService').getRestaurantLocations()
    });
  },

});
