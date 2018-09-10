package ch8.spring_data_jpa.p3_entity_versions.service;


import ch8.spring_data_jpa.p3_entity_versions.entities.SingerAudit;

import java.util.List;

public interface SingerAuditService {

    List<SingerAudit> findAll();

    SingerAudit findById(Long id);

    SingerAudit save(SingerAudit singerAudit);

    SingerAudit findByRevision(Long id, int revision);
}
