package mg.rova.demo.domain.ports.primary;

import mg.rova.demo.domain.entities.Todo;

public interface TodoService extends IsService<Todo, Long>, FindTodoByTextUseCase {
}
