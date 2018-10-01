package ch12.httpinvoker.server;

import ch12.httpinvoker.api.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

import java.util.stream.Stream;

@SpringBootApplication
public class Application {

    @Autowired
    private SingerService singerService;

    @Bean(name = "/singer")
    HttpInvokerServiceExporter singerService() {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(singerService);
        exporter.setServiceInterface(SingerService.class);
        return exporter;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
        ctx.close();
    }

    @Bean
    public CommandLineRunner run(final ApplicationContext ctx) {
        return args -> {
            Stream.of(ctx.getBeanDefinitionNames())
                    .forEach(System.out::println);
        };
    }
}
