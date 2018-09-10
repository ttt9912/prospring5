package ch8.spring_data_jpa.p3_entity_versions.service;

import ch8.spring_data_jpa.p3_entity_versions.entities.SingerAudit;
import ch8.spring_data_jpa.p3_entity_versions.repository.SingerAuditRepository;
import com.google.common.collect.Lists;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
 * AuditReader: retrieve history records
 */
@Service("singerAuditService")
@Transactional
public class SingerAuditServiceImpl implements SingerAuditService {
    private static final Logger logger = LoggerFactory.getLogger(SingerAuditServiceImpl.class);

    @Autowired
    private SingerAuditRepository singerAuditRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public SingerAudit findByRevision(final Long id, final int revision) {
        // access history records via AuditReader (spring)

        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.find(SingerAudit.class, id, revision);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SingerAudit> findAll() {
        return Lists.newArrayList(singerAuditRepository.findAll());
    }

    @Override
    public SingerAudit findById(final Long id) {
        return singerAuditRepository.findById(id).get();
    }

    @Override
    public SingerAudit save(final SingerAudit singerAudit) {
        SingerAudit savedSinger = singerAuditRepository.save(singerAudit);
        logger.info("Inserted singer with id={}", savedSinger.getId());
        return savedSinger;
    }
}
