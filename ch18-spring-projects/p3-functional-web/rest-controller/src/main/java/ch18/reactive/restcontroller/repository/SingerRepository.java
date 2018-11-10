package ch18.reactive.restcontroller.repository;

import ch18.reactive.restcontroller.data.Singer;
import org.springframework.data.repository.CrudRepository;

/*
 * data access via spring-data (as usual)
 */
public interface SingerRepository extends CrudRepository<Singer, Long> {
}
