package ru.mels.webpagewordscounter.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Swagger
 *
 * @author Meleshkin Alexandr
 * @since 02.02.2022
 */
@Configuration
@OpenAPIDefinition(info = @Info(title = "Word statistics counter",
        version = "1.0.0",
        description = "API allows to get used word frequency on a webpage by given URL."))
public class SwaggerConfiguration {
}
