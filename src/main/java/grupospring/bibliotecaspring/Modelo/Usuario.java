package grupospring.bibliotecaspring.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "dni", nullable = false, length = 15)
    @NotBlank(message = "Este campo no puede estar vacío")
    @NotEmpty(message = "Este campo no puede estar vacío")
    @NotNull(message = "Este campo no puede estar vacio")
    private String dni;

    @Column(name = "nombre", nullable = false, length = 100)
    @NotBlank(message = "Este campo no puede estar vacío")
    @NotEmpty(message = "Este campo no puede estar vacío")
    @NotNull(message = "Este campo no puede estar vacio")
    @Pattern(regexp = "^[a-zA-Z0-9]{1,100}$", message = "Nombre no válido")
    private String nombre;

    @Column(name = "email", nullable = false, length = 100)
    @NotBlank(message = "Este campo no puede estar vacío")
    @NotEmpty(message = "Este campo no puede estar vacío")
    @NotNull(message = "Este campo no puede estar vacio")
    @Pattern(regexp = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z]{2,7}$", message = "formato de correo no válido")
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank(message = "Este campo no puede estar vacío")
    @NotEmpty(message = "Este campo no puede estar vacío")
    @NotNull(message = "Este campo no puede estar vacio")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,12}")
    private String password;

    @Lob
    @Column(name = "tipo", nullable = false)
    @NotBlank(message = "Este campo no puede estar vacío")
    @NotEmpty(message = "Este campo no puede estar vacío")
    @NotNull(message = "Este campo no puede estar vacio")
    @Pattern(regexp = "^(Normal|Administrador)$", message = "tipo no valido")
    private String tipo;

    @Column(name = "penalizacionHasta")
    private LocalDate penalizacionHasta;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference("usuario-prestamo")
    private List<Prestamo> prestamos;

    public Usuario(){
        this.prestamos = new ArrayList<>();
    }

    public Usuario(String dni, String nombre, String password, String tipo){
        this.dni=dni;
        this.nombre=nombre;
        this.password=password;
        this.tipo=tipo;
        this.prestamos = new ArrayList<>();
    }

    public Usuario(String dni, String nombre, String password, String tipo, LocalDate penalizacionHasta){
        this.dni=dni;
        this.nombre=nombre;
        this.password=password;
        this.tipo=tipo;
        this.penalizacionHasta=penalizacionHasta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getPenalizacionHasta() {
        return penalizacionHasta;
    }

    public int getPrestamosActivos(){
        int contador=0;
        LocalDate fecha_actual = LocalDate.now();
        for(int i=0;i<this.prestamos.size();i++){
            if(prestamos.get(i).getFechaDevolucion().isAfter(fecha_actual)){
                contador++;
            }
        }
        return contador;
    }

    public void setPenalizacionHasta(LocalDate penalizacionHasta) {
        this.penalizacionHasta = penalizacionHasta;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public boolean validarDni (){
        //Lógica de comprobación del dni.
        return true;
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", tipo='" + tipo + '\'' +
                ", penalizacionHasta=" + penalizacionHasta +
                ", prestamos=" + prestamos +
                "\n}";
    }
}