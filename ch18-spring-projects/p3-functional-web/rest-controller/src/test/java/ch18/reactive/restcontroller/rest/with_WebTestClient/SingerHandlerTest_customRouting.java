package ch18.reactive.restcontroller.rest.with_WebTestClient;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/*
 * .bindToServer() connects to an actual running application
 *
 * .bindToRouterFunction() uses a custom routing
 */
public class SingerHandlerTest_customRouting {

    private static WebTestClient client;

    @BeforeAll
    public static void setup() {
        final RouterFunction<?> routerFunction = RouterFunctions.route(
                RequestPredicates.GET("/test"),
                request -> ServerResponse.ok().build()
        );

        client = WebTestClient
                .bindToRouterFunction(routerFunction)
                .build();
    }

    @Test
    public void testCustomRouting() {
        client.get().uri("/test").exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();
    }
}
