package com.tienda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class TiendaAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaAppApplication.class, args);
	}

}
