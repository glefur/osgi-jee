'use strict';

var project = angular.module('osgi.jee.samples.ui.views.styling', ['ngRoute']);

project.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/styling', {
    templateUrl: 'views/styling/styling.html',
    controller: 'StylingCtrl'
  });
}]);
project.controller('StylingCtrl', function($rootScope) {

	$rootScope.pageName = 'Styling';
	$rootScope.pageDesc = 'Text and paragraph styling';

});
