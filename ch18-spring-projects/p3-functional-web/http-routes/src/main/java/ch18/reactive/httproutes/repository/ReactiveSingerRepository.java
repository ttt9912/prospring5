package ch18.reactive.httproutes.repository;

import ch18.reactive.httproutes.data.Singer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * this is not spring-data
 */
public interface ReactiveSingerRepository {
    Mono<Singer> findById(Long id);

    Flux<Singer> findAll();

    Mono<Void> save(Mono<Singer> singerMono);
}
