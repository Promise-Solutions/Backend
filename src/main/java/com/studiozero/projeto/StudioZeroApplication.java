package com.studiozero.projeto;

import org.springframework.boot.SpringApplication;

import java.util.TimeZone;

public class StudioZeroApplication {
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		SpringApplication.run(StudioZeroApplication.class, args);
	}
}
