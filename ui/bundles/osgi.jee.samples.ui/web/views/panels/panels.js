'use strict';

var project = angular.module('osgi.jee.samples.ui.views.panels', ['ngRoute']);

project.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/panels', {
    templateUrl: 'views/panels/panels.html',
    controller: 'PanelsCtrl'
  });
}]);
project.controller('PanelsCtrl', function($rootScope) {

	$rootScope.pageName = 'Panels';
	$rootScope.pageDesc = 'Panels & Wells';

});
