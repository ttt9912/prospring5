package ch.jasypt.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

/*
 * Jasypt (Java Simplified Encryption) Spring Boot provides utilities for
 * encrypting property sources, such as property files in Spring Boot applications.
 *
 * The application will do the job of decrypting it and retrieving the original value.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.setProperty("jasypt.encryptor.password", "password");
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner run(final ApplicationContext ctx) {
        return args -> {
            JasyptPropertyService service = ctx
                    .getBean(JasyptPropertyService.class);
            System.out.println(service.getProperty());

            Environment environment = ctx.getBean(Environment.class);
            System.out.println(service.getPasswordUsingEnvironment(environment));
        };
    }

}
