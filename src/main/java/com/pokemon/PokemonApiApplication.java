package com.pokemon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.pokemon")
public class PokemonApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonApiApplication.class, args);
	}

}
