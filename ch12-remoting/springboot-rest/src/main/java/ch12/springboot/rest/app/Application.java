package ch12.springboot.rest.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Json serialization is supported by default in spring boot
 *
 * context tests unter src/test/
 */
@SpringBootApplication(scanBasePackages = "ch12.springboot.rest")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
