import Ember from 'ember';

export default Ember.Component.extend({

  didInsertElement(...args) {
    this._super(...args);
    this.renderGoogleMap($('.map-canvas')[0]);
  },

  renderGoogleMap(container) {
    let options = {
      center: new window.google.maps.LatLng(43.9, 18),
      zoom: 9,
    };

    let map = new window.google.maps.Map(container, options);

    let markerPosition;
    if (this.get('latitude') === 0 && this.get('longitude') === 0) {
      markerPosition = zoomBounds.getCenter();
      this.set('defaultMerkerPosition', markerPosition);
    } else {
      markerPosition = new google.maps.LatLng(43.9, 18.4);
    }

    let marker = new google.maps.Marker({
      position: markerPosition,
      draggable: false,
    });

    marker.setMap(map);
    this.set('marker', marker);

  }
});
