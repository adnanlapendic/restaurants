import Ember from 'ember';

export default Ember.Controller.extend({

  userService: Ember.inject.service('user-service'),

  email: null,
  password: null,

  actions: {
    authenticate() {

      var email = this.get('email');
      var password = this.get('password');

      this.get('userService').login(email, password);
    },
  },
});
