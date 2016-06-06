'use strict';

var project = angular.module('osgi.jee.samples.ui.angularui.views.modal', ['ngRoute', 'ui.bootstrap']);

project.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/modal', {
    templateUrl: 'angular-ui/views/modal/modal.html',
    controller: 'ModalCtrl'
  });
}]);

project.controller('ModalCtrl', function($rootScope, $scope, $uibModal) {

	$rootScope.pageName = 'Modal';
	$rootScope.pageDesc = 'Example of Bootstrap modals using AngularUI directives';


	$scope.items = ['item1', 'item2', 'item3'];

	$scope.animationsEnabled = true;

	$scope.open = function (size) {

	var modalInstance = $uibModal.open({
	    animation: $scope.animationsEnabled,
	    templateUrl: 'myModalContent.html',
	    controller: 'ModalInstanceCtrl',
	    size: size,
	    resolve: {
	      items: function () {
	        return $scope.items;
	      }
	    }
	  });

	modalInstance.result.then(function (selectedItem) {
	      $scope.selected = selectedItem;
	    }, function () {
	    });
	  };

	  $scope.toggleAnimation = function () {
	    $scope.animationsEnabled = !$scope.animationsEnabled;
	  };
	
});

project.controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, items) {

  $scope.items = items;
  $scope.selected = {
    item: $scope.items[0]
  };

  $scope.ok = function () {
    $uibModalInstance.close($scope.selected.item);
  };

  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  };
});