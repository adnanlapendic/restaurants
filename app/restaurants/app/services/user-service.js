import Ember from 'ember';

export default Ember.Service.extend({


  login(email, password) {
    Ember.$.ajax({
      url: "/api/v1/login",
      type: "POST",
      data: {
        email: email,
        password: password
      },
    }).then(function(resp) {
      alert("Login sucsessful");
    }).catch(function(error) {
      alert("invalid username or password")
    });
  },


  registerNewUser(firstName, lastName, email, phone, password) {
    Ember.$.ajax({
      url: "/api/v1/register",
      type: "POST",
      data: {
        firstName: firstName,
        lastName: lastName,
        email: email,
        phone: phone,
        password: password
      },
    }).then(function(resp) {
      alert("Register sucsessful");
    }).catch(function(error) {
      alert("ERROR ERROR ERROR")
    });
  },
});
