package com.flowbank.user.query.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "User Query API", version = "v1"))
@SecurityScheme(
        in = SecuritySchemeIn.HEADER,
        type = SecuritySchemeType.HTTP,
        name = "bearerAuth",
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class OpenApi30Config {
}