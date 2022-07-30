package mg.rova.demo.domain.ports.primary;

import java.util.List;

import mg.rova.demo.domain.entities.IsEntity;

public interface IsService<E extends IsEntity<I>, I> {

	E find(I id);

	List<E> findAll();

	E save(E entity);

	void delete(E entity);
}
