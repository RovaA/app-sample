package mg.rova.demo.domain.ports.secondary;

import mg.rova.demo.domain.entities.Todo;

import java.util.List;

public interface IsTodoRepository {

    List<Todo> findAll();

    List<Todo> findByText(String term);
}
