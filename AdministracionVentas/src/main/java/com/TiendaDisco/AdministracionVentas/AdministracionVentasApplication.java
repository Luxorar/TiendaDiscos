package com.TiendaDisco.AdministracionVentas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdministracionVentasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministracionVentasApplication.class, args);
	}

}
