package com.itec.xchangeservice;

import io.github.cdimascio.dotenv.Dotenv;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
@Slf4j

@OpenAPIDefinition
        (
                info = @Info
                        (
                                title = "Real-time Global XChange rates",
                                version = "1.0.0",
                                description = "",
                                contact = @Contact(
                                        name = "iTech Development",
                                        url = "www.itech.co.za",
                                        email = "support@itech.co.za"
                                ),
                                license = @License(
                                        name = "iTech Development license",
                                        url = "www.itech.co.za/licensing"
                                )
                        )
        )

public class XChangeServiceApplication {

    public static void main(String[] args) {
        // Load environment variables from .env file
        Dotenv dotenv = Dotenv.configure().load();

        // Access the environment variables
        String dbUsername = dotenv.get("DB_USERNAME");
        String dbPassword = dotenv.get("DB_PASSWORD");
        String dbName = dotenv.get("DB_NAME");
        String dbHost = dotenv.get("DB_HOST");

        System.setProperty("DB_USERNAME", dbUsername);
        System.setProperty("DB_PASSWORD", dbPassword);
        System.setProperty("DB_NAME", dbName);
        System.setProperty("DB_HOST", dbHost);

        SpringApplication.run(XChangeServiceApplication.class, args);

    }

}
