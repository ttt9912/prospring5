package ch18.reactive.restcontroller.rest.with_ExchangeFunction;

import ch18.reactive.restcontroller.data.Singer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.ExchangeFunctions;
import reactor.core.publisher.Mono;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
 * ExchangeFunction: used to exchange a ClientRequest for a delayed ClientResponse
 * alternative to WebClient
 *
 * Mono.block(): Subscribe to this Mono and block indefinitely until a next signal
 *               is received Returns that value, or null if the Mono completes empty
 *
 * assertAll(Executable e): group assertions
 */
// Application must be running!
public class SingerHandlerTest {
    private static final String HOST = "localhost";
    private static final int PORT = 8080;
    private static ExchangeFunction exchange;

    @BeforeAll
    public static void setup() {
        exchange = ExchangeFunctions.create(new ReactorClientHttpConnector());
    }


    @Test
    public void getSinger() {
        URI uri = URI.create(String.format("http://%s:%d/singers/1", HOST, PORT));
        ClientRequest request = ClientRequest.create(HttpMethod.GET, uri).build();

        Mono<Singer> singerMono = SingerHandlerTest.exchange.exchange(request)
                .flatMap(r -> r.bodyToMono(Singer.class));
        Singer singer = singerMono.block();

        assertAll("singer",
                () -> assertEquals("John", singer.getFirstName()),
                () -> assertEquals("Mayer", singer.getLastName()));
    }

    @Test
    public void checkHeaders() {
        URI uri = URI.create(String.format("http://%s:%d/singers/1", HOST, PORT));
        ClientRequest request = ClientRequest.create(HttpMethod.GET, uri).build();
        Mono<ClientResponse> response = SingerHandlerTest.exchange.exchange(request);

        assertEquals(HttpStatus.OK, response.block().statusCode());
    }

}