package grupospring.bibliotecaspring.Controlador;


import grupospring.bibliotecaspring.Modelo.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/ejemplares")
@CacheConfig(cacheNames = {"ejemplares"})
public class EjemplarController {
    EjemplarRepository ejemplarRepository;
    BookRepository bookRepository;
    public EjemplarController() {}

    @Autowired
    public EjemplarController(EjemplarRepository ejemplarRepository, BookRepository bookRepository) {
        this.ejemplarRepository = ejemplarRepository;
        this.bookRepository = bookRepository;
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
    public ResponseEntity<Ejemplar> add(@Valid @RequestBody EjemplarDTO ejemplarDTO) {
        Libro libro = this.bookRepository.findById(ejemplarDTO.getIsbn())
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        Ejemplar ejemplar = new Ejemplar(libro, ejemplarDTO.getEstado());
        this.ejemplarRepository.save(ejemplar);
        return ResponseEntity.ok(ejemplar);
    }
}
