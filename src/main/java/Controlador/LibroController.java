package Controlador;

import grupospring.bibliotecaspring.Modelo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/libros")
public class LibroController {
    BookRepository bookRepository;
    public LibroController() {}

    @Autowired
    public LibroController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
}
