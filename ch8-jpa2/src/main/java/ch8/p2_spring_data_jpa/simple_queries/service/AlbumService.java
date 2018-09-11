package ch8.p2_spring_data_jpa.simple_queries.service;


import ch8.entity.Album;
import ch8.entity.Singer;

import java.util.List;

public interface AlbumService {
    List<Album> findBySinger(Singer singer);

    List<Album> findByTitleLike(String title);
}
