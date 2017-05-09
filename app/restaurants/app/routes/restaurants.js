import Ember from 'ember';

export default Ember.Route.extend({

  restaurantService: Ember.inject.service('restaurant-service'),
  // userService: Ember.inject.service('user-service'),

  model(params) {
    return this.get('restaurantService').getAllRestaurants();

  }

});
