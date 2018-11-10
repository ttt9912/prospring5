package ch18.reactive.httproutes.repository;

import ch18.reactive.httproutes.data.Singer;
import org.springframework.data.repository.CrudRepository;

/*
 * data access via spring-data (as usual)
 */
public interface SingerRepository extends CrudRepository<Singer, Long> {
}
