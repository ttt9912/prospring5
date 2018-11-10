package ch18.reactive.rxjava2.rest;

import ch18.reactive.rxjava2.data.Singer;
import ch18.reactive.rxjava2.repository.Rx2SingerRepository;
import io.reactivex.Flowable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class ReactiveSingerController {

    @Autowired
    private Rx2SingerRepository rx2SingerRepository;

    @GetMapping(value = "/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flowable<Singer> all() {
        Flowable<Singer> singers = rx2SingerRepository.findAll();
        Flowable<Long> periodFlowable = Flowable.interval(1, TimeUnit.SECONDS);
        return singers.zipWith(periodFlowable, ((singer, aLong) -> {
            Thread.sleep(aLong);
            return singer;
        }));
    }

    @GetMapping(value = "/one/{id}")
    public Single<Singer> one(@PathVariable Long id) {
        return rx2SingerRepository.findById(id);
    }
}
