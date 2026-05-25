package com.TiendaDisco.AdministracionDescuentos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdministracionDescuentosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministracionDescuentosApplication.class, args);
	}

}
