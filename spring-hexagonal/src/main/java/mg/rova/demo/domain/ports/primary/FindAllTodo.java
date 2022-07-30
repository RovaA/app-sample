package mg.rova.demo.domain.ports.primary;

import mg.rova.demo.domain.entities.Todo;

import java.util.List;

public interface FindAllTodo {

    List<Todo> findAll();
}
