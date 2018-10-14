package ch.springboot.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "ch.springboot.data")
@EntityScan("ch.springboot.data")
public class AppConfig {
}
