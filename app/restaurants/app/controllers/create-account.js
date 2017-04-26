import Ember from 'ember';

export default Ember.Controller.extend({
  userService: Ember.inject.service('user-service'),

  firstName: null,
  lastName: null,
  email: null,
  phone: null,
  password: null,
  confirmPassword: null,

  actions: {

    register() {

      var firstName = this.get('firstName');
      var lastName = this.get('lastName');
      var email = this.get('email');
      var phone = this.get('phone');
      var password = this.get('password');
      var confirmPassword = this.get('confirmPassword');

      this.get('userService').registerNewUser(firstName, lastName, email, phone, password);

    },
  },

});
