package com.TiendaDisco.CarritoCompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Clase principal del microservicio de Carrito de Compras.
 * <p>Inicializa la aplicacion Spring Boot y habilita la comunicacion
 * con otros microservicios a traves de Feign.</p>
 *
 * @author Diego Barria
 * @author Fernando Castillo
 * @author Luis Villalon
 * @version 1.0.0
 */
@SpringBootApplication
@EnableFeignClients
public class CarritoComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarritoComprasApplication.class, args);
	}

}
