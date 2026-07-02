package com.TiendaDisco.AdministracionUsuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal del microservicio de Administracion de Usuarios.
 * <p>Inicializa la aplicacion Spring Boot para la gestion de usuarios
 * y administradores del sistema.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@SpringBootApplication
public class AdministracionUsuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministracionUsuarioApplication.class, args);
	}

}
