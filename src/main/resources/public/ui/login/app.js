/**
 * 
 */
angular.module('todo', [ 'ionic' ])

.controller('TodoCtrl', function($scope, $ionicModal) {
	$scope.tasks = [ {
		title : '大大大'
	}, {
		title : 'www.runoob.com'
	}, {
		title : '啊啊啊'
	}, {
		title : 'www.runoob.com'
	} ];

	$ionicModal.fromTemplateUrl('new-task.html', function(modal) {
		$scope.taskModal = modal;
	}, {
		scope : $scope,
		animation : 'slide-in-up'
	});

});