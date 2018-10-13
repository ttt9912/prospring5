package ch13.app;

import ch13.service.SingerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = "ch13")
public class App {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(App.class, args);
        ctx.close();
    }

    @Bean
    public CommandLineRunner run(final ApplicationContext ctx, final SingerService singerService) {
        return args -> {
            SingerService rmiSingerService = ctx.getBean(SingerService.class);
            rmiSingerService.findAll()
                    .forEach(System.out::println);
        };
    }
}
