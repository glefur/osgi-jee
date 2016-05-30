'use strict';

var project = angular.module('osgi.jee.samples.ui.views.tables', ['ngRoute']);

project.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/tables', {
    templateUrl: 'views/tables/tables.html',
    controller: 'TablesCtrl'
  });
}]);
project.controller('TablesCtrl', function($rootScope) {

	$rootScope.pageName = 'Tables';
	$rootScope.pageDesc = 'Tables available features';

});
