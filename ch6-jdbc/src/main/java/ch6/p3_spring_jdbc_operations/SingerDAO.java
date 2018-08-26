package ch6.p3_spring_jdbc_operations;

import ch6.entity.Singer;

import java.util.List;

interface SingerDAO {

    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);

    void update(Singer singer);
}
