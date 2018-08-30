package ch7;

import ch7.entity.Singer;

import java.util.List;

interface SingerDao {

    List<Singer> findAll();

    List<Singer> findAllWithAlbums();

    Singer findById(Long id);
}
