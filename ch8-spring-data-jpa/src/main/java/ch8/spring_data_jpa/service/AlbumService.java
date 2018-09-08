package ch8.spring_data_jpa.service;


import ch8.spring_data_jpa.entity.Album;
import ch8.spring_data_jpa.entity.Singer;

import java.util.List;

public interface AlbumService {
    List<Album> findBySinger(Singer singer);

    List<Album> findByTitleLike(String title);
}
