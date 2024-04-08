package com.learning.cardservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Card microservice REST API Documentation",
				description = "SecureBank Card microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Asima Sabat",
						email = "official.site@gmail.com",
						url = "https://www.sampleurl.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.sampleurl.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "SecureBank Loan microservice REST API Documentation",
				url = "https://www.sampleurl.com/swagger-ui.html"
		)
)
public class CardsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsServiceApplication.class, args);
	}

}
