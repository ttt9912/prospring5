package ch8.p2_spring_data_jpa.entity_versions;

import org.springframework.data.repository.CrudRepository;

interface SingerRepository extends CrudRepository<Singer, Long> {
}
