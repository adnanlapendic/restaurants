import Ember from 'ember';

export default Ember.Route.extend({

  restaurantService: Ember.inject.service('restaurant-service'),

  model(params) {
    return this.get('restaurantService').getSingleRestaurant(params.id)
  }

});
