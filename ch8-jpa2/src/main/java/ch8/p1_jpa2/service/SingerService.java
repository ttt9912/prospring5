package ch8.p1_jpa2.service;

import ch8.entity.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();
    List<Singer> findAllWithAlbums();
    Singer findById(Long id);

    Singer save(Singer singer);
    void delete(Singer singer);

    List<Singer> findAllByNativeQuery();
    List<Singer> findAllByNativeQuery_ResultSetMapping();

    List<Singer> findByCriteriaQuery(String firstName, String lastName);

}
