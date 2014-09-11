var todoWizard = angular.module('todoApp', []);

function mainController($scope, $http) {
	$scope.formData = {};

	$http.get('/api/todos').success(function(data) {
		$scope.todos = data;
		console.log("get todos");
		console.log(data);
	}).error(function(data) {
		console.log("Error: " + data);
	});

	$scope.createTodo = function() {
		$http.post('/api/todos', $scope.formData).success(function(data) {
			$scope.formData = {};
			$scope.todos.push(data);
			console.log(data);
		}).error(function(data) {
			console.log('Error');
			console.log(data);
		});
	};

	$scope.deleteTodo = function(id) {
		$http.delete('/api/todos/' + id).success(function(data) {
			$scope.todos = data;
			console.log(data);
		}).error(function(data) {
			console.log("Error: " + data);
		});
	};
}