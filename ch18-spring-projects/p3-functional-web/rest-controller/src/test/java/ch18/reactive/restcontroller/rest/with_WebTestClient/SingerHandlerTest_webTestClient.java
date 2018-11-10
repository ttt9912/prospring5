package ch18.reactive.restcontroller.rest.with_WebTestClient;

import ch18.reactive.restcontroller.data.Singer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/*
 * WebTestClient:
 * - integration testing support for Spring WebFlux
 * - Testing version of the WebClient
 * - does not need a running server (like MockMvc)
 *
 */
// Application must be running!
public class SingerHandlerTest_webTestClient {

    private static WebTestClient client;

    @BeforeAll
    public static void setup() {
        client = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:8080")
                .build();
    }

    @Test
    public void getSinger() {
        client.get().uri("/singers/1").exchange()
                .expectStatus().isOk()
                .expectBody(Singer.class)
                .consumeWith(seer ->
                {
                    Singer singer = seer.getResponseBody();

                    assertAll("singer",
                            () -> assertNotNull(singer));
                });
    }

    @Test
    public void getSingerNotFound() {
        client.get().uri("/singers/99").exchange()
                .expectStatus().isNotFound()
                .expectBody().isEmpty();
    }

    @Test
    public void getAll() {
        client.get().uri("/singers").accept(MediaType.TEXT_EVENT_STREAM).exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Singer.class).consumeWith(Assertions::assertNotNull);
    }
}