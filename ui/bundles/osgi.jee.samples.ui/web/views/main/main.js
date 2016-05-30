'use strict';

var project = angular.module('osgi.jee.samples.ui.views.main', ['ngRoute']);

project.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/main', {
    templateUrl: 'views/main/main.html',
    controller: 'MainCtrl'
  });
}]);
project.controller('MainCtrl', function($rootScope) {

	$rootScope.pageName = 'Main';
	$rootScope.pageDesc = 'Main page of this sample';

});
