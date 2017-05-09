import DS from 'ember-data';

export default DS.Model.extend({

  firstName: DS.attr(),
  lastName: DS.attr(),
  email: DS.attr(),
  token: DS.attr(),
  phone: DS.attr(),
  id: DS.attr(),
  country: DS.attr(),
  city: DS.attr(),
  password: DS.attr(),
  confirmPassword: DS.attr(),

});
