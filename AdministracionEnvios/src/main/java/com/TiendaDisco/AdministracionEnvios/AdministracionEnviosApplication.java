package com.TiendaDisco.AdministracionEnvios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Clase principal del microservicio de Administracion de Envios.
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@SpringBootApplication
@EnableFeignClients
public class AdministracionEnviosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministracionEnviosApplication.class, args);
	}

}
