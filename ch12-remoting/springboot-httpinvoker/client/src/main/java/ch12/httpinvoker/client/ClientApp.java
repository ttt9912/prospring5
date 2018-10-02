package ch12.httpinvoker.client;

import ch12.httpinvoker.api.service.SingerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/*
 * Server app must be running
 */
@SpringBootApplication
public class ClientApp {

    // retrieves SingerServiceImpl from Server and registers it
    // in ApplicationContext as a bean of type SingerService
    @Bean
    public HttpInvokerProxyFactoryBean invoker() {
        HttpInvokerProxyFactoryBean invoker = new HttpInvokerProxyFactoryBean();
        invoker.setServiceUrl("http://localhost:9090/singerService");
        invoker.setServiceInterface(SingerService.class);
        return invoker;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(ClientApp.class, args);
        ctx.close();
    }

    @Bean
    public CommandLineRunner run(final ApplicationContext ctx) {
        return args -> {
            SingerService rmiSingerService = ctx.getBean(SingerService.class);
            rmiSingerService.findAll().forEach(System.out::println);
        };
    }
}
