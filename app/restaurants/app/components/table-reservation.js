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
  time: ["08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00",
    "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30",
    "20:00", "20:30", "21:00", "21:30", "22:00"
  ],

  actions: {
    checkForTable() {

      var people = this.get('name123');
      var date = this.get('date');
      var time = this.get('time123');
      var idRestaurant = this.get('restaurant.id');


      this.get('restaurantService').checkReservationAvailability(people, date, time, idRestaurant)
        .then(() => {
          this.set('haveResponse', true);
        })
        .catch((response) => {
          this.set('notification', response);
        });

    },
  },
  haveResponse: false,
});
