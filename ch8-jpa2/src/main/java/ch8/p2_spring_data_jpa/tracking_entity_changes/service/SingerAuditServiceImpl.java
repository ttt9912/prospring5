package ch8.p2_spring_data_jpa.tracking_entity_changes.service;

import ch8.p2_spring_data_jpa.tracking_entity_changes.entities.SingerAudit;
import ch8.p2_spring_data_jpa.tracking_entity_changes.repository.SingerAuditRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("singerAuditService")
@Transactional
public class SingerAuditServiceImpl implements SingerAuditService {
    private static final Logger logger = LoggerFactory.getLogger(SingerAuditServiceImpl.class);

    @Autowired
    private SingerAuditRepository singerAuditRepository;

    @Transactional(readOnly = true)
    public List<SingerAudit> findAll() {
        return Lists.newArrayList(singerAuditRepository.findAll());
    }

    public SingerAudit findById(final Long id) {
        return singerAuditRepository.findById(id).orElse(null);
    }

    public SingerAudit save(final SingerAudit singerAudit) {
        SingerAudit savedSinger = singerAuditRepository.save(singerAudit);
        logger.info("Inserted singer with id={}", savedSinger.getId());
        return savedSinger;
    }
}
