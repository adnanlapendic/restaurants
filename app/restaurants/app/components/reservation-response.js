import Ember from 'ember';

export default Ember.Component.extend({
  restaurantService: Ember.inject.service('restaurant-service'),

  reservationResponse: null,

  model() {
    return this.get('restaurantService').checkReservationAvailability();
  }
});
