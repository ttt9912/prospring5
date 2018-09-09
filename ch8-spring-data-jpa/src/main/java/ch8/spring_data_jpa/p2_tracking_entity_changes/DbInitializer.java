package ch8.spring_data_jpa.p2_tracking_entity_changes;

import ch8.spring_data_jpa.p2_tracking_entity_changes.entities.SingerAudit;
import ch8.spring_data_jpa.p2_tracking_entity_changes.repository.SingerAuditRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;

@Service
public class DbInitializer {

    private Logger logger = LoggerFactory.getLogger(DbInitializer.class);

    @Autowired
    SingerAuditRepository singerRepository;

    @PostConstruct
    public void initDB() {
        logger.info("Starting database initialization...");

        SingerAudit singer = new SingerAudit();
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(Date.valueOf("1977-09-16"));

        singerRepository.save(singer);

        singer = new SingerAudit();
        singer.setFirstName("Eric");
        singer.setLastName("Clapton");
        singer.setBirthDate(Date.valueOf("1945-02-30"));

        singerRepository.save(singer);

        singer = new SingerAudit();
        singer.setFirstName("John");
        singer.setLastName("Butler");
        singer.setBirthDate(Date.valueOf("1975-03-01"));

        singerRepository.save(singer);
        logger.info("Database initialization finished.");
    }
}
