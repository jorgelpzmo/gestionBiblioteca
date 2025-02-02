package Controlador;

import grupospring.bibliotecaspring.Modelo.Usuario;
import grupospring.bibliotecaspring.Modelo.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    private boolean validarDni(String dni) {
        String regex = "^[0-9]{8}[A-Za-z]$";

        if (dni != null && dni.matches(regex)) {
            String numeroDni = dni.substring(0, 8);
            String letraDni = dni.substring(8).toUpperCase();
            int numero = Integer.parseInt(numeroDni);
            String[] letras = {"T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S", "Q", "V", "H", "L", "C", "K", "E"};
            String letraCalculada = letras[numero % 23];
            return letraCalculada.equals(letraDni);
        }

        return false;
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
    public ResponseEntity<?> add(@Valid @RequestBody Usuario usuario){
        if(validarDni(usuario.getDni())) {
            Usuario usuarioPersistido = this.repositorioUsuarios.save(usuario);
            return ResponseEntity.ok(usuario);
        }else{
            String mensaje = "DNI no válido";
            return ResponseEntity.badRequest().body(mensaje);
        }
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
