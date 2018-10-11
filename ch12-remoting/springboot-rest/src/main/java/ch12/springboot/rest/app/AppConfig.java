package ch12.springboot.rest.app;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
 * @EnableJpaRepositories: required if Repositories are not in the same package as @SpringBootApplication
 *
 * @EntityScan: scans entities for persistence context
 *
 * DataConfig with all the hibernate configs can be omitted in spring boot
 */
@Configuration
@EnableJpaRepositories(basePackages = "ch12.springboot.rest.data")
@EntityScan("ch12.springboot.rest.data")
public class AppConfig {
}
