package ch18.reactive.rxjava2.repository;

import ch18.reactive.rxjava2.data.Singer;
import org.springframework.data.repository.CrudRepository;

/*
 * data access via spring-data (as usual)
 */
public interface SingerRepository extends CrudRepository<Singer, Long> {
}
