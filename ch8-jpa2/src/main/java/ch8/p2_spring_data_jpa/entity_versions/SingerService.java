package ch8.p2_spring_data_jpa.entity_versions;

import java.util.List;

interface SingerService {

    List<Singer> findAll();

    Singer findById(Long id);

    Singer save(Singer singer);

    Singer findByRevision(Long id, int revision);
}
