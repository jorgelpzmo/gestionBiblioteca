package grupospring.bibliotecaspring.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @Column(name = "isbn", nullable = false, length = 20)
    @NotBlank(message = "Este campo no puede estar vacío")
    @NotEmpty(message = "Este campo no puede estar vacío")
    @NotNull(message = "Este campo no puede estar vacio")
    @Pattern(regexp = "^\\d{3}-\\d-\\d{3}-\\d{5}-\\d$", message = "El codigo isbn tiene que tener una estructura como la siguiente 978-0-596-52068-7")
    private String isbn;

    @Column(name = "titulo", nullable = false, length = 200)
    @NotBlank(message = "Este campo no puede estar vacío")
    @NotEmpty(message = "Este campo no puede estar vacío")
    @NotNull(message = "Este campo no puede estar vacio")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,200}$", message = "El titulo debe de tener una longitud maxima de 200 y solo puede contener caracteres alfanumericos")
    private String titulo;

    @Column(name = "autor", nullable = false, length = 100)
    private String autor;

    @OneToMany(mappedBy = "libro")
    @JsonManagedReference("ejemplar")
    private List<Ejemplar> ejemplares = new ArrayList<>();

    public Libro(){
    }

    public Libro(String isbn, String titulo, String autor) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<Ejemplar> getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(List<Ejemplar> ejemplars) {
        this.ejemplares = ejemplars;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", ejemplares=" + ejemplares +
                '}';
    }
}