package ch18.reactive.reactiveclient.repository;

import ch18.reactive.reactiveclient.data.Singer;
import org.springframework.data.repository.CrudRepository;

/*
 * data access via spring-data (as usual)
 */
public interface SingerRepository extends CrudRepository<Singer, Long> {
}
