package ch.app;

import ch.dto.Pojo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    Pojo samePackage() {
        return new Pojo("Goodbye");
    }
}
