package ch18.reactive.reactiveclient.repository;

import ch18.reactive.reactiveclient.data.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/*
 * Reactive Repository makes use of spring-data singer Repository
 */
@Service
public class ReactiveSingerRepositoryImpl implements ReactiveSingerRepository {

    @Autowired
    private SingerRepository singerRepository;

    // emits a Singer object, or else an onComplete signal
    @Override
    public Mono<Singer> findById(final Long id) {
        return Mono.justOrEmpty(singerRepository.findById(id));
    }

    // returns a stream
    @Override
    public Flux<Singer> findAll() {
        return Flux.fromIterable(singerRepository.findAll());
    }


    @Override
    public Mono<Void> save(final Mono<Singer> singerMono) {
        return singerMono
                .doOnNext(singer1 -> singerRepository.save(singer1))
                .thenEmpty(Mono.empty());
    }
}
