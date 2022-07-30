Application.Controllers.controller('TodoDetailController', ['$scope', '$routeParams', 'todoService', function($scope, $routeParams, todoService) {
	var todoDetail = this;
	
	$scope.editMode = false;
	
	let todoId = $routeParams.id; 
	
	var entityReadOnly;

	todoService.find(todoId)
		.then(
			response => {
				todoDetail.entity = response.data; 
				entityReadOnly = angular.copy(todoDetail.entity);
			},
			response => console.log("error on finding Entity"));
	
	todoDetail.changeMode = function() {
		$scope.editMode = !$scope.editMode;
	};
	
	todoDetail.updateTodo = function() {
		todoService.createOrUpdate(todoDetail.entity).then(function(response) {
			todoDetail.entity = response.data;
			entityReadOnly = angular.copy(todoDetail.entity);
			todoDetail.changeMode();
		});
	};
	
	todoDetail.noChange = function() {
			return (angular.equals(entityReadOnly.text, todoDetail.entity.text) && angular.equals(entityReadOnly.done, todoDetail.entity.done))
	}
  }]);