package Controlador;

import grupospring.bibliotecaspring.Modelo.BookRepository;
import grupospring.bibliotecaspring.Modelo.Ejemplar;
import grupospring.bibliotecaspring.Modelo.EjemplarRepository;
import grupospring.bibliotecaspring.Modelo.Libro;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Pattern;

@Validated
@RestController
@RequestMapping("/ejemplares")
@CacheConfig(cacheNames = {"ejemplares"})
public class EjemplarController {
    EjemplarRepository ejemplarRepository;
    public EjemplarController() {}

    @Autowired
    public EjemplarController(EjemplarRepository ejemplarRepository) {
        this.ejemplarRepository = ejemplarRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Ejemplar>> getAll() {
        return ResponseEntity.ok(this.ejemplarRepository.findAll());
    }

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<?> get(@PathVariable Integer id) {
            try {
                Thread.sleep(3000);
                Ejemplar ejemplar = this.ejemplarRepository.findById(id).orElseThrow();
                return ResponseEntity.ok(ejemplar);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
    }

    @Validated
    @PostMapping
    public ResponseEntity<Ejemplar> add(@Valid @RequestBody Ejemplar ejemplar) {
        this.ejemplarRepository.save(ejemplar);
        return ResponseEntity.ok(ejemplar);
    }
}
