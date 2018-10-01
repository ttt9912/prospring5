package ch.config;

import ch.dto.Pojo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigTwo {

    @Bean
    Pojo differentPackage() {
        return new Pojo("Golden");
    }
}
