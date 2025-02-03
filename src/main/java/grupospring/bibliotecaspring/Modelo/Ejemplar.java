package grupospring.bibliotecaspring.Modelo;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "ejemplar")
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "isbn", nullable = false)
    @JsonBackReference("ejemplar")
    @JsonIgnore
    private Libro libro;

    @ColumnDefault("'Disponible'")
    @Lob
    @Column(name = "estado")
    private String estado;
    //@Pattern(regexp = "^(Disponible|Prestado|Dañado)$", message = "Estado no valido")

    @OneToMany(mappedBy = "ejemplar")
    @JsonManagedReference("ejemplar-prestamo")
    private List<Prestamo> prestamos = new ArrayList<>();

    @JsonProperty("isbn")
    public String getIsbn() {
        return libro.getIsbn();
    }
    public Ejemplar(){}

    public Ejemplar(Libro libro, String estado) {
        this.libro = libro;
        this.estado = estado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro isbn) {
        this.libro = isbn;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    @Override
    public String toString() {
        return "Ejemplar{" +
                "id=" + id +
                ", libro=" + libro.getTitulo() +
                ", estado='" + estado + '\'' +
                "\n}";
    }
}