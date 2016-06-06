'use strict';

// Declare app level module which depends on views, and components
angular.module('osgi.jee.samples.ui.angularui', [
  'ngRoute',
  'osgi.jee.samples.ui.angularui.views.main',
  'osgi.jee.samples.ui.angularui.views.modal'
]).
config(['$routeProvider', function($routeProvider) {
  $routeProvider.otherwise({redirectTo: '/main'});
}]);
