package ch16.springboot.app;

import ch16.springboot.service.SingerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
 * Resources directories:
 * spring boot web app uses /resources as web resources directory,
 * no webapp directory is needed
 */
@SpringBootApplication(scanBasePackages = "ch16.springboot")
@EnableJpaRepositories(basePackages = "ch16.springboot.data")
@EntityScan("ch16.springboot.data")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    // Todo: bootstrap, date formatting



    @Bean
    public CommandLineRunner run(final ApplicationContext ctx) {
        return args -> {
            SingerService singerService = ctx.getBean(SingerService.class);
            singerService.findAll().forEach(System.out::println);
        };
    }
}
