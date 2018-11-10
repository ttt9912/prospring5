package ch18.reactive.reactiveclient;

import ch18.reactive.reactiveclient.data.Singer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

/*
 * same application as in rest-controller Module with additional WebClient
 *
 * Client is declared in the same SpringBootApplication for shared context
 *
 * WebClient:
 * - reactive nonblocking alternative to RestTemplate
 * - replaces AsyncRestTemplate
 * - can consume and subscribe to streams (e.g. Flux)
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    WebClient client() {
        return WebClient.create("http://localhost:8080/api");
    }

    @Bean
    CommandLineRunner clr(WebClient webClient) {
        return args ->
                webClient
                        .get().uri("/all")
                        .accept(MediaType.TEXT_EVENT_STREAM)
                        .exchange()
                        .flatMapMany(cr -> cr.bodyToFlux(Singer.class))
                        .subscribe(System.out::println);
    }
}
