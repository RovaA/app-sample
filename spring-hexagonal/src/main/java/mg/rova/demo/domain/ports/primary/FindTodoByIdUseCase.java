package mg.rova.demo.domain.ports.primary;

import mg.rova.demo.domain.entities.Todo;

public interface FindTodoByIdUseCase {

    Todo find(Long id);
}
