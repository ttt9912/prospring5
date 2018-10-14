package ch.springboot.app;

import ch.springboot.service.SingerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/*
 * testing spring boot is not part of prospring5 book
 */
@SpringBootApplication(scanBasePackages = "ch.springboot")
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner run(final ApplicationContext ctx) {
        return args -> {
            SingerService singerService = ctx.getBean(SingerService.class);
            singerService.findAll().forEach(System.out::println);
        };
    }
}
