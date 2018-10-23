package ch16.springboot.service;

import ch16.springboot.data.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);

    Singer findById(Long id);

    Singer save(Singer singer);

    void delete(Singer singer);
}
