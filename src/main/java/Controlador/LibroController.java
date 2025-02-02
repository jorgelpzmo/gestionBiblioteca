package Controlador;

import grupospring.bibliotecaspring.Modelo.BookRepository;
import grupospring.bibliotecaspring.Modelo.Libro;
import jakarta.transaction.Transactional;
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
@RequestMapping("/libros")
@CacheConfig(cacheNames = {"libros"})
public class LibroController {
    BookRepository bookRepository;
    public LibroController() {}

    @Autowired
    public LibroController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public ResponseEntity<Iterable<Libro>> getAll() {
        return ResponseEntity.ok(this.bookRepository.findAll());
    }

    @GetMapping("/{isbn}")
    @Cacheable
    public ResponseEntity<?> get(@PathVariable String isbn) {
        if(Pattern.matches("^\\d{3}-\\d-\\d{3}-\\d{5}-\\d$", isbn)) {
            try {
                Thread.sleep(3000);
                Libro l = this.bookRepository.findById(isbn).orElseThrow();
                return ResponseEntity.ok(l);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }else{
            return ResponseEntity.ok("ISBN invalido");
        }
    }

    @PostMapping
    public ResponseEntity<Libro> add(@Valid @RequestBody Libro l) {
        this.bookRepository.save(l);
        return ResponseEntity.ok(l);
    }

    @PutMapping
    public ResponseEntity<Libro> update(@Valid @RequestBody Libro l) {
        this.bookRepository.save(l);
        return ResponseEntity.ok(l);
    }
    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> delete(@PathVariable String isbn) {
        if(Pattern.matches("^\\d{3}-\\d-\\d{3}-\\d{5}-\\d$", isbn)) {
            Libro l = this.bookRepository.findById(isbn).orElseThrow();
            this.bookRepository.delete(l);
            return ResponseEntity.ok("Libro eliminado correctamente");
        }
        else{
            return ResponseEntity.ok("ISBN invalido" + isbn);
        }
    }

}
