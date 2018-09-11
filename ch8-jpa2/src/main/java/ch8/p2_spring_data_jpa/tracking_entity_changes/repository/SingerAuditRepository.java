package ch8.p2_spring_data_jpa.tracking_entity_changes.repository;

import ch8.p2_spring_data_jpa.tracking_entity_changes.entities.SingerAudit;
import org.springframework.data.repository.CrudRepository;

public interface SingerAuditRepository extends CrudRepository<SingerAudit, Long> {
}
