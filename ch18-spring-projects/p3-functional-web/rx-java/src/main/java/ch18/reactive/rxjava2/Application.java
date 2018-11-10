package ch18.reactive.rxjava2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * This application is equivalent to the other reactive web apps in this module
 * Difference:
 *  - other apps use Reactor as Reactive Streams implementation
 *  - this app uses RxJava2 as Reactive Streams implementation
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // client could be HttpClient (Java9)
}
