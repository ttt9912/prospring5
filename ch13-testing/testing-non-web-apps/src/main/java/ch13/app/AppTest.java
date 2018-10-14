package ch13.app;

import ch13.config.DataSourceConfig;
import ch13.config.ServiceConfig;
import ch13.service.SingerService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.stream.Stream;

/*
 * tests under /test/
 */
public class AppTest {

    // Run via run config using Profile=dev
    // VM Argument: -Dspring.profiles.active=dev
    public static void main(String... args) {
        AnnotationConfigApplicationContext ctx =
                new AnnotationConfigApplicationContext(DataSourceConfig.class, ServiceConfig.class);

        System.out.print("\nactive profiles: ");
        ConfigurableEnvironment environment = ctx.getEnvironment();
        Stream.of(environment.getActiveProfiles()).forEach(System.out::println);

        System.out.println("\n--- Singers ---");
        SingerService singerService = ctx.getBean(SingerService.class);
        singerService.findAll().forEach(System.out::println);
    }
}
