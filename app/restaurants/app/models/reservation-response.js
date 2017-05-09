import DS from 'ember-data';

export default DS.Model.extend({

  tablesLeft: DS.attr(),
  bestTime: DS.attr(),
  restaurantId: DS.attr(),
  restaurantName: DS.attr(),
  restaurantImage: DS.attr(),
  date: DS.attr(),
  people: DS.attr(),
  time: DS.attr(),

});
