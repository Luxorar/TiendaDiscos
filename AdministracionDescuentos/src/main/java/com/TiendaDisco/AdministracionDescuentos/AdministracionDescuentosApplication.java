package com.TiendaDisco.AdministracionDescuentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Clase principal del microservicio de Administracion de Descuentos.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@SpringBootApplication
@EnableFeignClients
public class AdministracionDescuentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministracionDescuentosApplication.class, args);
	}

}
