package ch6.p2_jdbcTemplate;

import ch6.entity.Singer;

import java.util.List;

interface SingerDAO {

    // find
    String findNameById(Long id);

    String findFirstNameById(final Long id);

    List<Singer> findAll();

    // crud
    void insert(Singer singer);

    void update(Singer singer);

    void delete(Singer singer);

    // with Relationships
    List<Singer> findAllWithAlbums();

    void insertWithDetail(Singer singer);

}
