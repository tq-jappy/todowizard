var todoWizard = angular.module('todoApp', [ 'ui.bootstrap' ]);

function mainController($scope, $http) {
	$scope.formData = {};
	$scope.todoList = [];
	$scope.doneList = [];
	
	$http.get('/api/todos').success(function(data) {
		angular.forEach(data, function(todo, key) {
			if (todo.completed) {
				$scope.doneList.push(todo);
			} else {
				$scope.todoList.push(todo);
			}
		});
		console.log("get todoList");
		console.log(data);
	}).error(function(data) {
		console.log("Error: " + data);
	});

	$scope.createTodo = function() {
		$http.post('/api/todos', $scope.formData).success(function(data) {
			$scope.formData = {};
			$scope.todoList.push(data);
			console.log(data);
		}).error(function(data) {
			console.log('Error');
			console.log(data);
		});
	};

	$scope.completeTodo = function(todo) {
		todo.completed = true;
		$http.put('/api/todos/' + todo.id, todo).success(function(data) {
			$scope.todoList.splice($scope.todoList.indexOf(todo), 1);
			$scope.doneList.push(todo);
			console.log(data);
		}).error(function(data) {
			console.log("Error: " + data);
		});
	};
	
	$scope.clearDoneList = function() {
		$scope.doneList.forEach(function(todo) {
			$http.delete('/api/todos/' + todo.id).success(function() {
				$scope.doneList.splice($scope.doneList.indexOf(todo), 1);
				console.log('delete with id: ' + todo.id);
			}).error(function(err) {
				console.log('Error: ');
				console.log(err);
			});
		});
	}
}