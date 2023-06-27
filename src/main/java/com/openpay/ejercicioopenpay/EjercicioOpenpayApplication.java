package com.openpay.ejercicioopenpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.openpay.apis.marvel.mavel.*", "com.openpay.ejercicioopenpay"})
public class EjercicioOpenpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjercicioOpenpayApplication.class, args);
	}

}
