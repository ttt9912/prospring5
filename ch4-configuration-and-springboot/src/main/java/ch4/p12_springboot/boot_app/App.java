package ch4.p12_springboot.boot_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
class App {

    private static Logger logger = LoggerFactory.getLogger(HelloWorld.class);

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(App.class, args); // startet einen tomcat auf localhost:8080

        logger.info("The beans you were looking for:");
        Arrays.stream(ctx.getBeanDefinitionNames()).forEach(logger::info);

        HelloWorld bean = ctx.getBean(HelloWorld.class);
        bean.sayHi();

        System.in.read(); // sodass die app nicht gleich terminiert
        ctx.close();
    }
}
