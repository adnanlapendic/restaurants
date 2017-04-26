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
  this.route('home');
  this.route('create-account');
  this.route('restaurantDetails', {
    path: 'restaurant/:id'
  });
});

export default Router
