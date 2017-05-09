import Ember from 'ember';

export default Ember.Controller.extend({

  userService: Ember.inject.service('user-service'),
  session: Ember.inject.service('session'),
  restaurantService: Ember.inject.service('restaurant-service'),


  // actions: {
  //   logout11() {
  //     this.get('session').invalidate();
  //     this.get('userService').logout();
  //     this.transitionToRoute('home');
  //
  //   }
  // },

});
