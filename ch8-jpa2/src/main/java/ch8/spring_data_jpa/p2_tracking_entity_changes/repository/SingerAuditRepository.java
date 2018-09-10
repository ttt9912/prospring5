package ch8.spring_data_jpa.p2_tracking_entity_changes.repository;

import ch8.spring_data_jpa.p2_tracking_entity_changes.entities.SingerAudit;
import org.springframework.data.repository.CrudRepository;

public interface SingerAuditRepository extends CrudRepository<SingerAudit, Long> {
}
