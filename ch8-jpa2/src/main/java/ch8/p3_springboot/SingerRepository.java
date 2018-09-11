package ch8.p3_springboot;

import ch8.entity.Singer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

interface SingerRepository extends CrudRepository<Singer, Long> {

    List<Singer> findByFirstName(String firstName);

    List<Singer> findByFirstNameAndLastName(String firstName, String lastName);
}
