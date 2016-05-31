'use strict';

var project = angular.module('osgi.jee.samples.ui.views.pics', ['ngRoute']);

project.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/pics', {
    templateUrl: 'views/pics/pics.html',
    controller: 'PicsCtrl'
  });
}]);
project.controller('PicsCtrl', function($rootScope) {

	$rootScope.pageName = 'Pics';
	$rootScope.pageDesc = 'Here is some examples for Pics (Glyphicons & Images)';

});
