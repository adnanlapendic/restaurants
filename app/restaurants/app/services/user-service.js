import Ember from 'ember';

export default Ember.Service.extend({

  currentUser: null,

  login(email, password) {
    return Ember.$.ajax({
      url: "/api/v1/login",
      type: "POST",
      data: {
        email: email,
        password: password
      },
      xhrFields: {
        withCredentials: true
      },
    }).then(function(response) {
      return response;
    });

  },


  registerNewUser(firstName, lastName, email, phone, password, confirmPassword) {
    return Ember.$.ajax({
      url: "/api/v1/register",
      type: "POST",
      data: {
        firstName: firstName,
        lastName: lastName,
        email: email,
        phone: phone,
        password: password,
        confirmPassword: confirmPassword
      },
      xhrFields: {
        withCredentials: true
      },
    }).then(function(response) {
      return response;
    });
  },

  getCurrentUser() {
    return Ember.$.ajax({
      url: '/api/v1/currentUser',
      method: 'GET',
      contentType: "application/json",
      type: "json",
    }).then(function(user) {
      return user;
    });
  },

  logout() {
    return Ember.$.ajax({
      url: '/api/v1/logout',
      method: 'GET',
      contentType: "application/json",
      type: "json",
    }).then(function(data) {
      return data;
    });
  },

});
