package ch4.p12_springboot.web_app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
class WebApp {

    private static Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(WebApp.class, args);

        logger.info("App started");
        logger.info("go to localhost:8080/");

        System.in.read();
        ctx.close();
    }
}
