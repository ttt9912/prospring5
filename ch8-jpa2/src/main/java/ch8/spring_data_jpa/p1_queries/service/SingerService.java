package ch8.spring_data_jpa.p1_queries.service;


import ch8.spring_data_jpa.p1_queries.entity.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();

    List<Singer> findByFirstName(String firstName);

    List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}
