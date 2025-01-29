package grupospring.bibliotecaspring.Modelo;

import grupospring.bibliotecaspring.Modelo.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Usuario, Integer> {
}
