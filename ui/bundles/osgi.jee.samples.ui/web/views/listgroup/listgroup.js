'use strict';

var project = angular.module('osgi.jee.samples.ui.views.listgroup', ['ngRoute']);

project.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/listgroup', {
    templateUrl: 'views/listgroup/listgroup.html',
    controller: 'ListGroupCtrl'
  });
}]);
project.controller('ListGroupCtrl', function($rootScope) {

	$rootScope.pageName = 'ListGroups';
	$rootScope.pageDesc = 'List Groups';

});
