'use strict';

var project = angular.module('osgi.jee.samples.ui.views.grid', ['ngRoute']);

project.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/grid', {
    templateUrl: 'views/grid/grid.html',
    controller: 'GridCtrl'
  });
}]);
project.controller('GridCtrl', function($rootScope) {

	$rootScope.pageName = 'Grid';
	$rootScope.pageDesc = 'Examples for Bootstrap grid system';

});
