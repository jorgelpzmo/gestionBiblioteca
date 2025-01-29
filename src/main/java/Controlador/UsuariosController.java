package Controlador;

import grupospring.bibliotecaspring.Modelo.Usuario;
import grupospring.bibliotecaspring.Modelo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    UserRepository repositorioUsuarios;
    public UsuariosController(){}

    @Autowired
    public UsuariosController(UserRepository repositorioUsuarios){
        this.repositorioUsuarios=repositorioUsuarios;
    }

    @GetMapping
    public ResponseEntity<Iterable<Usuario>> getAll(){
        return ResponseEntity.ok(this.repositorioUsuarios.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> get(@PathVariable Integer id){
        Usuario usuario = this.repositorioUsuarios.findById(id).orElseThrow();
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Usuario> add(@RequestBody Usuario usuario){
        Usuario usuarioPersistido = this.repositorioUsuarios.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
        Usuario usuarioPersistido = this.repositorioUsuarios.save(usuario);
        return ResponseEntity.ok(usuario);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        this.repositorioUsuarios.deleteById(id);
        return ResponseEntity.ok("Usuario con id: "+id+" eliminado");
    }

}
