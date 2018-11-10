package ch18.reactive.rxjava2.repository;

import ch18.reactive.rxjava2.data.Singer;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface Rx2SingerRepository {
    Single<Singer> findById(Long id);

    Flowable<Singer> findAll();

    Single<Void> save(Single<Singer> singerSingle);
}
