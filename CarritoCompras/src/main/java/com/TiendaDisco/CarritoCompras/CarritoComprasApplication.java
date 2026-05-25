package com.TiendaDisco.CarritoCompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CarritoComprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarritoComprasApplication.class, args);
	}

}
