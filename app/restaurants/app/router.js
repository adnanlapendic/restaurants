import Ember from 'ember';
import config from './config/environment';

const Router = Ember.Router.extend({
  location: config.locationType,
  rootURL: config.rootURL
});

Router.map(function() {
  this.route('restaurants', function() {
    this.route('restaurantDetails', {
      path: 'restaurant/:id'
    });
  });
  this.route('login');
  this.route('home', {
    path: '/'
  });
  this.route('create-account', {
    path: 'register'
  });
  this.route('restaurantDetails', {
    path: 'restaurant/:id'
  });
  this.route('complete-reservation', {
    path: 'reservation'
  });
});

export default Router
