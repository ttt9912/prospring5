package ch12.service;

import ch12.entity.Singer;

import java.util.List;

/*
 * Service to expose to remote clients
 */
public interface SingerService {
    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);

    Singer findById(Long id);

    Singer save(Singer singer);

    void delete(Singer singer);
}
