package io.eronalves.pawpals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(servers = @Server(url = "/pawpals"), info = @Info(title = "Pawpals API", description = "API to found your new pet friend!"))
@SpringBootApplication
public class PawpalsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PawpalsApplication.class, args);
	}

}
