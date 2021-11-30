package br.com.apppetshoprest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {
	
	@Bean
	public OpenAPI customizarSwagger() {
		return new OpenAPI()
				.info(new Info()
						.title("RESTful API Petshop/Santander")
						.version("v1")
						.description("API desenvolvida para o Projeto Integrador/Santander")
						.termsOfService("http://swagger.io/terms")
						.license(new License().name("Apache 2.0").url("http://springdoc.org"))
				);
}
	
	

}
