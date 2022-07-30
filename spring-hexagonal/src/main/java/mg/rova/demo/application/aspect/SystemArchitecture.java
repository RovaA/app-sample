package mg.rova.demo.application.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import mg.rova.demo.domain.entities.Todo;

@Aspect
@Component
public class SystemArchitecture {

	@Pointcut("execution(* mg.rova.demo.infrastructure.adapters.primary.controller.TodoController.create(..))")
	private void createTodo() {
	}

	@Pointcut("execution(* mg.rova.demo.infrastructure.adapters.primary.controller.TodoController.find(..))")
	private void findTodo() {
	}

	@Pointcut("execution(* mg.rova.demo.infrastructure.adapters.primary.controller.TodoController.list(..))")
	private void todoList() {
	}
	
	@AfterReturning(pointcut="mg.rova.demo.application.aspect.SystemArchitecture.createTodo()", returning="todo")
	public void createTodo(Todo todo) {
		Logger.getLogger(this.getClass().getName()).info(todo.toString());
	}
	
	@AfterReturning(pointcut="mg.rova.demo.application.aspect.SystemArchitecture.findTodo()", returning="todo")
	public void findTodo(Todo todo) {
		Logger.getLogger(this.getClass().getName()).info(todo.toString());
	}
	
	@AfterReturning(pointcut="mg.rova.demo.application.aspect.SystemArchitecture.todoList()", returning="entities")
	public void todoList(List<Todo> entities) {
		Logger.getLogger(this.getClass().getName()).info(entities.toString());
	}
}
