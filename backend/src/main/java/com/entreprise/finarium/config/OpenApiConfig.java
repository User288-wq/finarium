package com.entreprise.finarium.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI finariumOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("FINARIUM API")
                .description("API du référentiel des instruments financiers")
                .version("1.0.0")
                .contact(new Contact()
                    .name("Squad FINARIUM")
                    .email("finarium@entreprise.com")));
    }
}
