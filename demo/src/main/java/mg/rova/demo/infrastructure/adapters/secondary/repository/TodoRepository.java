package mg.rova.demo.infrastructure.adapters.secondary.repository;

import mg.rova.demo.domain.ports.secondary.IsTodoRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import mg.rova.demo.domain.entities.Todo;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>, IsTodoRepository {

    List<Todo> findByText(String term);

    @Procedure("findTodoByText")
    List<Todo> findByProcedureText(@Param("term") String term);

}
