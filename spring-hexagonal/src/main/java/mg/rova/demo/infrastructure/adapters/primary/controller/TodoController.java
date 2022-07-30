package mg.rova.demo.infrastructure.adapters.primary.controller;

import mg.rova.demo.domain.entities.Todo;
import mg.rova.demo.domain.ports.primary.FindTodoByTextUseCase;
import mg.rova.demo.domain.ports.primary.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

	@Autowired
	protected FindTodoByTextUseCase findTodoByTextUseCase;

	@Autowired
	protected TodoService service;

	@PostMapping
	public Todo create(@RequestBody Todo todo) {
		return service.save(todo);
	}

	@PostMapping("/{id}")
	public Todo update(@RequestBody Todo todo) {
		return service.save(todo);
	}

	@GetMapping
	public List<Todo> list() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Todo find(@PathVariable Long id) {
		return service.find(id);
	}

	@GetMapping("/find/{term}")
	public List<Todo> findByText(@PathVariable String term) {
		return findTodoByTextUseCase.findTodo(term);
	}

	@DeleteMapping
	public void delete(@RequestParam Long id) {
		service.delete(service.find(id));
	}
}
