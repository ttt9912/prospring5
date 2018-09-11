package ch8.p2_spring_data_jpa.tracking_entity_changes.service;

import ch8.p2_spring_data_jpa.tracking_entity_changes.entities.SingerAudit;

import java.util.List;

public interface SingerAuditService {
    List<SingerAudit> findAll();

    SingerAudit findById(Long id);

    SingerAudit save(SingerAudit singerAudit);
}
