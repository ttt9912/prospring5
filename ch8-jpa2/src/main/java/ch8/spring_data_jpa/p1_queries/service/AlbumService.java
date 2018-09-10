package ch8.spring_data_jpa.p1_queries.service;


import ch8.spring_data_jpa.p1_queries.entity.Album;
import ch8.spring_data_jpa.p1_queries.entity.Singer;

import java.util.List;

public interface AlbumService {
    List<Album> findBySinger(Singer singer);

    List<Album> findByTitleLike(String title);
}
