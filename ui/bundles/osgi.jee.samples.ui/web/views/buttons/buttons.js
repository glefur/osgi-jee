'use strict';

var project = angular.module('osgi.jee.samples.ui.views.buttons', ['ngRoute']);

project.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/buttons', {
    templateUrl: 'views/buttons/buttons.html',
    controller: 'ButtonsCtrl'
  });
}]);
project.controller('ButtonsCtrl', function($rootScope) {

	$rootScope.pageName = 'Buttons';
	$rootScope.pageDesc = 'Buttons available features';

});
