 import Ember from 'ember';

 export default Ember.Controller.extend({

   userService: Ember.inject.service('user-service'),
   session: Ember.inject.service('session'),

   email: null,
   password: null,
   notification: null,

   actions: {
     login() {
       let {
         email,
         password
       } = this.getProperties('email', 'password');
       this.get('session').authenticate('authenticator:oauth2', email, password)
         .then(() => {
           this.transitionToRoute('home');
           this.set('email', "");
           this.set('password', "");
           this.set('notification', "");
         })
         .catch((response) => {
           this.set('notification', response);
           this.set('email', "");
           this.set('password', "");
         })

     },

   },
 });
