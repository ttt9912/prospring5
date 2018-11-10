package ch18.reactive.reactiveclient.rest;

import ch18.reactive.reactiveclient.data.Singer;
import ch18.reactive.reactiveclient.repository.ReactiveSingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

/*
 * rest controller directly returns a Flux but emits the objects
 * one by one every second
 *
 * MediaType.TEXT_EVENT_STREAM_VALUE: special type of plain text
 * server creates a response with media Content Type 'text/event-stream'
 *
 * each Response contains a 'data:' followed by the message (singer object)
 * followed by two '\n'
 */
@RestController
@RequestMapping("/api")
public class ReactiveSingerController {

    @Autowired
    private ReactiveSingerRepository reactiveSingerRepository;

    /*
     * Flux.zip() combines two streams
     *  1. Flux implementation returned by the reactive repository that contains singers
     *  2. stream containing an interval of seconds
     *
     * Flux.zip() waits for all the sources to emit one element and combine these elements
     * until any source completes
     */
    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Singer> oneByOne() {
        Flux<Singer> singerFlux = reactiveSingerRepository.findAll();
        Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(1));

        return Flux.zip(singerFlux, periodFlux).map(Tuple2::getT1);
    }

    @GetMapping(value = "/one/{id}")
    public Mono<Singer> one(@PathVariable Long id) {
        return reactiveSingerRepository.findById(id);
    }
}
