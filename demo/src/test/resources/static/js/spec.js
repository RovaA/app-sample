describe("TodoListController", function() {
	beforeEach(module('application.services', 'application.controllers'));

	var $scope = {}, $httpBackend, todoListController;

	beforeEach(inject(function($controller, $q, _$httpBackend_, todoService) {
		$httpBackend = _$httpBackend_;
		$httpBackend.when('GET', '/api/todo/list').respond([ "test", "test1" ]);
		todoListController = $controller('TodoListController', {
			'$scope' : $scope,
			'todoService' : todoService
		});
	}));

	afterEach(function() {
		$httpBackend.verifyNoOutstandingExpectation();
		$httpBackend.verifyNoOutstandingRequest();
	});

	it("On controller init", function() {
		$httpBackend.flush();
		
		expect($scope.searchModel).toEqual('');
		expect($scope.doneModel).toBe(false);
		expect(todoListController.todos).toEqual([ "test", "test1" ]);
	});

	it("addTodo", function() {
		$httpBackend.when('POST', '/api/todo/create').respond("test");
		$httpBackend.flush();
		todoListController.todos = [];
		todoListController.todoText = "test";

		todoListController.addTodo();
		$httpBackend.flush();

		expect(todoListController.todoText).toEqual('');
		expect(todoListController.todos).toEqual([ "test" ]);
	});

	it("remaining", function() {
		todoListController.todos = [ {
			done : 0
		}, {
			done : 0
		}, {
			done : 1
		}, {
			done : 0
		} ];
		
		expect(todoListController.remaining()).toEqual(3);
		$httpBackend.flush();
	});

	it("update", function() {
		var todoUpdate = { id : 1, name : 'expected' };
		$httpBackend.when('POST', '/api/todo/create').respond(todoUpdate);
		$httpBackend.flush();
		todoListController.todos = [{
			id : 0,
			name: 'test0'
		}, {
			id : 1,
			name: 'test1'
		}];
		
		todoListController.update(todoUpdate);
		$httpBackend.flush();
		
		expect(todoListController.todos[0].name).toEqual('test0');
		expect(todoListController.todos[1].name).toEqual('expected');
	});
});