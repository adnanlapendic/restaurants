import Ember from 'ember';

export default Ember.Component.extend({


  restaurantService: Ember.inject.service('restaurant-service'),

  restaurant: null,
  people: null,
  date: null,
  time: null,
  idRestaurant: null,
  haveResponse: null,
  reservationResponse: null,
  people: [2, 3, 4, 5, 6, 7, 8, 9, 10],

  actions: {
    checkForTable() {

      var people = this.get('name123');
      var date = this.get('date');
      var time = this.get('time');
      var idRestaurant = this.get('restaurant.id');


      this.get('restaurantService').checkReservationAvailability(people, date, time, idRestaurant)
        .then(() => {
          this.set('haveResponse', true);


          var respons = this.get('restaurantService').checkReservationAvailability();
          debugger
          this.set('restaurantService.reservationResponse', this.get('restaurantService').checkReservationAvailability(people, date, time, idRestaurant))
        })
        .catch((response) => {
          this.set('notification', response);
        });

    },
  },
  haveResponse: false,
});
