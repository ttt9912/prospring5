package ch8.p3_springboot;

import ch8.entity.Instrument;
import org.springframework.data.repository.CrudRepository;

interface InstrumentRepository extends CrudRepository<Instrument, Long> {
}
