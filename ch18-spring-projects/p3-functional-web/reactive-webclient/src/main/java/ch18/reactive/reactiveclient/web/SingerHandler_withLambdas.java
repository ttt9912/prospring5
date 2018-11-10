package ch18.reactive.reactiveclient.web;

import ch18.reactive.reactiveclient.data.Singer;
import ch18.reactive.reactiveclient.repository.ReactiveSingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/*
 * list() and save() can be easily rewritten as lambdas
 *
 * HandlerFunction<ServerResponse>: essentially is a Function<Request, Response>
 *     is side effects free because it returns the response directly
 *     instead of taking it as a parameter
 */
public class SingerHandler_withLambdas {

    @Autowired
    private ReactiveSingerRepository reactiveSingerRepository;

    public HandlerFunction<ServerResponse> list =
            serverRequest -> ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(reactiveSingerRepository.findAll(), Singer.class);

    public HandlerFunction<ServerResponse> save =
            serverRequest -> ServerResponse.ok()
                    .build(reactiveSingerRepository.save(serverRequest.bodyToMono(Singer.class)));


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
}
