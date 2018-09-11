package ch8.p2_spring_data_jpa.entity_versions;

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
@Service("singerService")
@Transactional
class SingerServiceImpl implements SingerService {
    private static final Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);

    @Autowired
    private SingerRepository singerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Singer findByRevision(final Long id, final int revision) {
        // access history records via AuditReader (spring)

        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        return auditReader.find(Singer.class, id, revision);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Singer> findAll() {
        return Lists.newArrayList(singerRepository.findAll());
    }

    @Override
    public Singer findById(final Long id) {
        return singerRepository.findById(id).orElse(null);
    }

    @Override
    public Singer save(final Singer singerAudit) {
        Singer savedSinger = singerRepository.save(singerAudit);
        logger.info("Inserted singer with id={}", savedSinger.getId());
        return savedSinger;
    }
}
