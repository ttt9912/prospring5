package ch10.p3_validation.with_jsr349_bean_validation.custom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/*
 * LocalValidatorFactoryBean: provides Bean Validation implementation
 *                            (by Hibernate)
 */
@Configuration
@ComponentScan(basePackages = "ch10.p3_validation.with_jsr349_bean_validation.custom")
class AppConfig {

    @Bean
    LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }
}
