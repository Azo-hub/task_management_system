package com.task_management_system;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name="Task Management System",
                        email="readone.cybernet@gmail.com",
                        url = "https://github.com/Azo-hub"
                ),
                description = "Task Management System Documentation",
                title = "Task Management System Specification",
                version = "1.0",
                license = @License(
                        name= "Apache-2.0 License",
                        url = "https://github.com/Azo-hub/task_management_system/blob/master/LICENSE"
        )

        )
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
