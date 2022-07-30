package mg.rova.demo.infrastructure.adapters.secondary.service.rest;

import mg.rova.demo.domain.entities.Todo;
import mg.rova.demo.domain.ports.primary.FindTodoByTextUseCase;
import mg.rova.demo.domain.ports.primary.TodoService;
import mg.rova.demo.infrastructure.adapters.secondary.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TodoServiceImpl extends AbsServiceImpl<Todo, Long, TodoRepository> implements TodoService, FindTodoByTextUseCase {

	@Autowired
	public TodoServiceImpl(TodoRepository repository) {
		super(repository);
	}

	@Override
	public Todo save(Todo entity) {
		return repository.save(entity);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Todo> findTodo(String text) {
		return repository.findByProcedureText(text);
	}
}
