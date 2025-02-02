package grupospring.bibliotecaspring.Modelo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan(basePackages = "Controlador")
@EnableCaching
public class BibliotecaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(BibliotecaSpringApplication.class, args);
    }

}
