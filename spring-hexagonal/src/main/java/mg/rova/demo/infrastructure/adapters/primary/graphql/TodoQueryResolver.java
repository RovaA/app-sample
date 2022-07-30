package mg.rova.demo.infrastructure.adapters.primary.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import mg.rova.demo.domain.entities.Todo;
import mg.rova.demo.domain.ports.primary.FindAllTodo;
import mg.rova.demo.domain.ports.primary.FindTodoByIdUseCase;
import mg.rova.demo.domain.ports.primary.FindTodoByTextUseCase;
import mg.rova.demo.infrastructure.adapters.secondary.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Primary
@Component
public class TodoQueryResolver implements GraphQLQueryResolver, FindTodoByTextUseCase, FindTodoByIdUseCase, FindAllTodo {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo find(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Id not found"));
    }

    @Transactional(readOnly = true)
    @Override
    public List<Todo> findTodo(String text) {
        return todoRepository.findByProcedureText(text);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Todo> findAll() {
        return todoRepository.findAll();
    }
}
