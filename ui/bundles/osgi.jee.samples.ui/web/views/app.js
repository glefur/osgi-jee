'use strict';

// Declare app level module which depends on views, and components
angular.module('osgi.jee.samples.ui', [
  'ngRoute',
  'osgi.jee.samples.ui.views.main',
  'osgi.jee.samples.ui.views.grid',
  'osgi.jee.samples.ui.views.styling',
  'osgi.jee.samples.ui.views.tables',
  'osgi.jee.samples.ui.views.buttons',
  'osgi.jee.samples.ui.views.forms',
  'osgi.jee.samples.ui.views.pics'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/main'});
}]);
