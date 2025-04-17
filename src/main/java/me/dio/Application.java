package me.dio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Initializes our RESTful API.
 * 
 * <p>
 * The {@link OpenAPIDefinition} annotation was used to enable HTTPS in the Swagger UI.
 * For more details, see the following post on Stack Overflow: 
 * https://stackoverflow.com/a/71132608/3072570
 * </p>
 */

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")}) // Esta anotação foi usada para habilitar HTTPS no Swagger UI, ou seja, para que o Swagger UI seja acessível via HTTPS. Pois o Railway funciona com HTTPS. Daí estava dando conflito.
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
