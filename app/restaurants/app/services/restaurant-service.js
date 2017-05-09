import Ember from 'ember';

export default Ember.Service.extend({

      reservationResponse: null,

      getAllRestaurants() {
        return Ember.$.ajax({
          url: '/api/v1/restaurant',
          method: 'GET',
        }).then(function(data) {

          return data;
        });
      },

      getRestaurantLocations() {
        return Ember.$.ajax({
          url: '/api/v1/getRestaurantsLocations',
          method: 'GET',
        }).then(function(data) {
          return data;
        });

      },

      getSingleRestaurant(id) {
        return Ember.$.ajax({
          url: "/api/v1/getRestaurantDetails",
          method: "POST",
          data: {
            id: id
          },
        }).then(function(resp) {
          return resp;
        }).catch(function(error) {
          alert("Error")
        });

      },

      checkReservationAvailability(people, date, time, restaurantId) {
        return Ember.$.ajax({
          url: "/api/v1/checkForReservation",
          method: "POST",
          data: {
            people: people,
            date: date,
            time: time,
            restaurantId: restaurantId
          },
          xhrFields: {
            withCredentials: true
          },
        }).then(function(resp) {
            var promise = new Ember.RSVP.Promise(function(resolve, reject) {
              debugger
              resolve(resp);
              // or reject
              reject(error);
              return promise;
            }).catch(function(error) {
              alert("Error")
            });
          },

        });
