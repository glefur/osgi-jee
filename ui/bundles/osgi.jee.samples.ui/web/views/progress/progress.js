'use strict';

var project = angular.module('osgi.jee.samples.ui.views.progress', ['ngRoute']);

project.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/progress', {
    templateUrl: 'views/progress/progress.html',
    controller: 'ProgressCtrl'
  });
}]);
project.controller('ProgressCtrl', function($rootScope) {

	$rootScope.pageName = 'Progress bars';
	$rootScope.pageDesc = 'Progress bars available features';

});
