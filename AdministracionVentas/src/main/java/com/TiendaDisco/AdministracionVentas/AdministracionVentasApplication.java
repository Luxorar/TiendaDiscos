package com.TiendaDisco.AdministracionVentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Clase principal del microservicio de Administracion de Ventas.
 * <p>Inicializa y configura la aplicacion Spring Boot, habilitando
 * la comunicacion con otros microservicios a traves de Feign.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@SpringBootApplication
@EnableFeignClients
public class AdministracionVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministracionVentasApplication.class, args);
	}

}
