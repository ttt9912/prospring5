package ch.app;

import ch.dto.Pojo;
import ch.service.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static ch.util.Util.printBeansOfType;

/*
 * @SpringBootApplication combines @Configuration, @EnableAutoConfiguration, @ComponentScan
 *
 * @SpringBootApplication Automatically detects:
 * - @Bean & @Configuration in the same class
 * - @Configuration classes in the same package
 * - @Component classes in the same package
 *
 * @Configuration classes from other classes can be included with:
 * - @ComponentScan(basePackages = "ch.config") (overrides the component scan in this package)
 * - @Import(ConfigTwo.class)
 */
@SpringBootApplication
// load configuration from other package
// @ComponentScan(basePackages = "ch.config") // package ch.app is not scanned anymore
// @Import(ConfigTwo.class)
public class App {

    @Bean
    Pojo hello() {
        return new Pojo("Hello");
    }

    @Configuration
    class NestedConfig {
        @Bean
        Pojo nested() {
            return new Pojo("Nested");
        }
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(App.class, args);

        printBeansOfType(Pojo.class, ctx);
        printBeansOfType(MyService.class, ctx);

        ctx.close();
    }

}
