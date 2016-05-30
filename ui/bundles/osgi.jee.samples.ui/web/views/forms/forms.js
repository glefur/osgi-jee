'use strict';

var project = angular.module('osgi.jee.samples.ui.views.forms', ['ngRoute']);

project.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/forms', {
    templateUrl: 'views/forms/forms.html',
    controller: 'FormsCtrl'
  });
}]);
project.controller('FormsCtrl', function($rootScope) {

	$rootScope.pageName = 'Forms';
	$rootScope.pageDesc = 'Forms available features';

});
