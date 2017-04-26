import DS from 'ember-data';

export default DS.Model.extend({

  name: DS.attr(),
  description: DS.attr(),
  city: DS.attr(),
  address: DS.attr(),
  cousine: DS.attr(),
  image: DS.attr(),
  coverImage: DS.attr(),
  latitude: DS.attr(),
  longitude: DS.attr(),

});
