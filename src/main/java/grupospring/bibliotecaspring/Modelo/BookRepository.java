package grupospring.bibliotecaspring.Modelo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Libro, Integer> {
}
