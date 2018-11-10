package ch18.reactive.restcontroller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Exposes reactive RestController: localhost:8080/api/all etc.
 *
 * webfluxtests under: /test/
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // client could be WebClient (see reactive-webclient module)

}
