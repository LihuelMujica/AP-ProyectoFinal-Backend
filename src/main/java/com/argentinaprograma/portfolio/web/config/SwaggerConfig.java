package com.argentinaprograma.portfolio.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    private final String controllersBasePackage = "com.argentinaprograma.portfolio.web.controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(controllersBasePackage))
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Portfolio API",
                "An api for managing my portfolio ",
                "0.0.1",
                "Terms of service",
                new Contact("Alex Lihuel Mujica", "https://www.linkedin.com/in/lihuelmujica/", "lihuelmujica@gmail.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}