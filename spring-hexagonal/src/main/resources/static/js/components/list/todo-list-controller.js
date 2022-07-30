Application.Controllers.controller('TodoListController', ['$scope', '$http', 'Todo', 'todoService', function($scope, $http, Todo, todoService) {

	$scope.searchModel = '';
	$scope.doneModel = false;

	const refresh = () => {
		Todo.query(function(todos) {
			$scope.todos = todos;
		});
	};

	$scope.addTodo = function() {
		var newTodo = new Todo();
		newTodo.creationDate = null;
        newTodo.updateDate = null;
		newTodo.id = null;
		newTodo.done = false;
		newTodo.text = $scope.todoText;

        newTodo.$save(function(todo) {
            $scope.todos.push(todo)
		});
        $scope.todoText = '';
	};

    $scope.remaining = function() {
		var count = 0;
		angular.forEach($scope.todos, function(todo) {
			count += todo.done ? 0 : 1;
		});
		return count;
	};

    $scope.update = function(todo) {
        todo.$save();
	};

    $scope.delete = function(todo) {
    	todo.$delete();
	};

    $scope.onSearch = function(term) {
    	if (!term || term.length === '') {
			refresh();
    		return;
		}
		todoService.findByText(term).then(function(response) {
			$scope.todos = angular.fromJson(response.data);
		}, function(error) {
			console.log(error);
		});
	};

	refresh();

}]);