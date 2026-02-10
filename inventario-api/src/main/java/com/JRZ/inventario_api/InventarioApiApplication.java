package com.JRZ.inventario_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import io.github.cdimascio.dotenv.Dotenv;
import com.JRZ.inventario_api.entity.Producto;
import com.JRZ.inventario_api.repository.ProductoRepository;

@SpringBootApplication
public class InventarioApiApplication {

	public static void main(String[] args) {
		// Cargar el archivo .env
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        
        // Inyectar las variables manualmente al sistema para que Spring las vea
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
		SpringApplication.run(InventarioApiApplication.class, args);

	}

	@Bean
	CommandLineRunner testConnection(ProductoRepository repository){
		return args -> {
			Producto p = new Producto();
			p.setNombre("LAPTOP");
			p.setPrecio(10000.00);

			repository.save(p);
			System.out.println("conexion exitosa producto guardado");
		};
	}
}
