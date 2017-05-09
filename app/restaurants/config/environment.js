/* eslint-env node */

module.exports = function(environment) {
  var ENV = {
    modulePrefix: 'restaurants',
    environment: environment,
    rootURL: '/',
    locationType: 'auto',
    EmberENV: {
      FEATURES: {
        // Here you can enable experimental features on an ember canary build
        // e.g. 'with-controller': true
      },
      EXTEND_PROTOTYPES: {
        // Prevent Ember Data from overriding Date.parse.
        Date: false
      }
    },

    APP: {
      // Here you can pass flags/options to your application instance
      // when it is created
    }
  };

  ENV.contentSecurityPolicy = {
    'script-src': "'self' 'unsafe-eval' https://*.googleapis.com https://*.gstatic.com",
    'img-src': "'self' https://*.googleapis.com https://*.gstatic.com",
    'font-src': "'self' https://*.gstatic.com",
    'style-src': "'self' 'unsafe-inline' https://*.googleapis.com"
  }

  ENV['ember-basic-dropdown'] = {
    destination: '<id-of-destination-element>'
  }

  ENV.googleMap = {
    apiKey: 'AIzaSyDn_Ky9YSJErjRegXQ0qrltq6g8tetc8Dw'
  }
  ENV.apiHost = "http://localhost:9000";
  ENV['ember-simple-auth'] = {
    authorizer: 'authorizer:token',
    baseURL: ''
  };
  ENV['ember-simple-auth-token'] = {
    refreshAccessTokens: false,
    authorizer: 'authorizer:token',
    identificationField: 'email',
    serverTokenEndpoint: ''
  };
  ENV['ember-simple-auth'].baseURL = ENV.apiHost;
  ENV['ember-simple-auth-token'].serverTokenEndpoint = `${ENV.apiHost}/api/v1/login`;



  if (environment === 'development') {
    // ENV.APP.LOG_RESOLVER = true;
    // ENV.APP.LOG_ACTIVE_GENERATION = true;
    // ENV.APP.LOG_TRANSITIONS = true;
    // ENV.APP.LOG_TRANSITIONS_INTERNAL = true;
    // ENV.APP.LOG_VIEW_LOOKUPS = true;
  }

  if (environment === 'test') {
    // Testem prefers this...
    ENV.locationType = 'none';

    // keep test console output quieter
    ENV.APP.LOG_ACTIVE_GENERATION = false;
    ENV.APP.LOG_VIEW_LOOKUPS = false;

    ENV.APP.rootElement = '#ember-testing';
  }

  if (environment === 'production') {

  }

  return ENV;
};
