import Ember from 'ember';

export default Ember.Controller.extend({

  userService: Ember.inject.service('user-service'),
  session: Ember.inject.service('session'),

  firstName: null,
  lastName: null,
  email: null,
  phone: null,
  password: null,
  confirmPassword: null,
  notification: null,

  actions: {
    register() {
      let {
        firstName,
        lastName,
        email,
        phone,
        password,
        confirmPassword
      } =
      this.getProperties('firstName', 'lastName', 'email', 'phone', 'password', 'confirmPassword');
      this.get('userService').registerNewUser(firstName, lastName, email, phone, password, confirmPassword)
        .then(() => {
          this.transitionToRoute('home');
          this.set('firstName', "");
          this.set('lastName', "");
          this.set('email', "");
          this.set('phone', "");
          this.set('password', "");
          this.set('confirmPassword', "");
        })
        .fail((response) => {
          // this.set('notification', response);
          console.log(response);
        })
        .catch((response) => {
          this.set('notification', response);
        });

    },
  },

});
