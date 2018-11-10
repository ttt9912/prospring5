package ch18.reactive.rxjava2.repository;

import ch18.reactive.rxjava2.data.Singer;
import io.reactivex.Flowable;
import io.reactivex.Single;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Rx2SingerRepositoryImpl implements Rx2SingerRepository {

    @Autowired
    private SingerRepository singerRepository;

    @Override
    public Single<Singer> findById(final Long id) {
        return Single.just(singerRepository.findById(id).get());
    }

    @Override
    public Flowable<Singer> findAll() {
        return Flowable.fromIterable(singerRepository.findAll());
    }

    @Override
    public Single<Void> save(final Single<Singer> singerSingle) {
        singerSingle.doOnSuccess(singer -> singerRepository.save(singer));
        return Single.just(null);
    }
}
