package ch18.reactive.reactiveclient.web;

import ch18.reactive.reactiveclient.data.Singer;
import ch18.reactive.reactiveclient.repository.ReactiveSingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/*
 * reactive handler
 *
 * @Controller is not used because it would just consume
 * the streams and return the contents in a view
 *
 * ServerRequest/ServerResponse: provide access to underlying http messages.
 * Both are fully reactive:
 *  - ServerRequest exposes body as Flux or Mono
 *  - Mono<ServerResponse> accepts any reactive stream as the body
 *
 * ServerResponse is immutable and has to be created using a builder
 *
 * URL Mappings are not defined on the handler (unlike when using @Controller)
 * instead a RouterFunction is defined in Configuration
 */
@Component
public class SingerHandler {

    @Autowired
    private ReactiveSingerRepository reactiveSingerRepository;

    public Mono<ServerResponse> list(ServerRequest request) {
        Flux<Singer> singers = reactiveSingerRepository.findAll();

        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(singers, Singer.class);
    }

    public Mono<ServerResponse> show(ServerRequest request) {
        Mono<Singer> singerMono = reactiveSingerRepository
                .findById(Long.valueOf(request.pathVariable("id")));

        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return singerMono.flatMap(singer ->
                ServerResponse.ok()
                        .contentType(APPLICATION_JSON)
                        .body(fromObject(singer)))
                .switchIfEmpty(notFound);
    }

    // bodyToMono(), bodyToFlux(): access request body
    public Mono<ServerResponse> save(ServerRequest request) {
        Mono<Singer> singerMono = request.bodyToMono(Singer.class);
        reactiveSingerRepository.save(singerMono);
        return ServerResponse.ok()
                .build(reactiveSingerRepository.save(singerMono));
    }
}
