package ch8.spring_data_jpa.p1_queries.repository;


import ch8.spring_data_jpa.p1_queries.entity.Album;
import ch8.spring_data_jpa.p1_queries.entity.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*
 * @Param: can be omitted if method parameter name equals query parameter name
 */
public interface AlbumRepository extends JpaRepository<Album, Long> {

    // query is inferred by spring
    List<Album> findBySinger(Singer singer);

    // custom query
    @Query("select a from Album a where a.title like %:title%")
    List<Album> findByTitleLike(@Param("title") String t);
}
