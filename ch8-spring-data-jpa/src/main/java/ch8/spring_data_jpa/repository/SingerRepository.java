package ch8.spring_data_jpa.repository;

import ch8.spring_data_jpa.entity.Singer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/*
 * The Repository Abstraction in Spring Data JPA wraps the underlying JPA EntityManager
 * and provides a simpler interface for JPA based data access.
 *
 * No need to provide a named query, spring data jpa will infer the query.
 *
 * Spring Repository extensions:
 * - CrudRepository (extends Repository)
 * - JpaRepository (extends CrudRepository)
 *
 * CrudRepository<[entity class], [id type]>
 *
 * Default: lazy fetching
 */
public interface SingerRepository extends CrudRepository<Singer, Long> {
    List<Singer> findByFirstName(String firstName);

    List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}
