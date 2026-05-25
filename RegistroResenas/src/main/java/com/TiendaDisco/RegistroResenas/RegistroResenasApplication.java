package com.TiendaDisco.RegistroResenas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class RegistroResenasApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistroResenasApplication.class, args);
	}

}
