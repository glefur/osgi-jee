'use strict';

// Declare app level module which depends on views, and components
var app = angular.module('osgi.jee.samples.ui.angularui', [
  'ui.bootstrap',
  'ngRoute',
  'osgi.jee.samples.ui.angularui.views.main',
  'osgi.jee.samples.ui.angularui.views.modal'
]);

app.config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/main'});
}]);
