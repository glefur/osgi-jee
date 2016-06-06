'use strict';

var project = angular.module('osgi.jee.samples.ui.angularui.views.main', ['ngRoute', 'ui.bootstrap']);

project.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/main', {
    templateUrl: 'angular-ui/views/main/main.html',
    controller: 'MainCtrl'
  });
}]);
project.controller('MainCtrl', function($rootScope) {

	$rootScope.pageName = 'Main';
	$rootScope.pageDesc = 'Main page of this sample';

});
