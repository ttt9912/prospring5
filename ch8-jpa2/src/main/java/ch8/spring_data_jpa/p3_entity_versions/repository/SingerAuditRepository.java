package ch8.spring_data_jpa.p3_entity_versions.repository;

import ch8.spring_data_jpa.p3_entity_versions.entities.SingerAudit;
import org.springframework.data.repository.CrudRepository;

public interface SingerAuditRepository extends CrudRepository<SingerAudit, Long> {
}
