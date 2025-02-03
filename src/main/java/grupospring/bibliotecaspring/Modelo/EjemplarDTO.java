package grupospring.bibliotecaspring.Modelo;

import jakarta.validation.constraints.Pattern;

public class EjemplarDTO {
    private int id;
    private String isbn;
    @Pattern(regexp = "^(Disponible|Prestado|Dañado)$", message = "Estado no valido")
    private String estado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
