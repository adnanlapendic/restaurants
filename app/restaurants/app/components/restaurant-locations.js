import Ember from 'ember';

export default Ember.Component.extend({

  restaurantService: Ember.inject.service('restaurant-service'),

  // locations: Ember.computed(function() {
  //   this.get('restaurantService').getRestaurantLocations()
  //     .then((response) => {
  //       console.log(response);
  //       return response;
  //     });
  // }),
  classNames: ['restaurant-locations-row'],

});
