package ch8.p3_springboot;

import ch8.entity.Album;
import ch8.entity.Instrument;
import ch8.entity.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Date;

@Service
class DbInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DbInitializer.class);

    @Autowired
    private SingerRepository singerRepository;

    @Autowired
    private InstrumentRepository instrumentRepository;

    @PostConstruct
    public void initDB() {
        logger.info("Starting database initialization...");

        final Instrument guitar = new Instrument();
        guitar.setInstrumentId("Guitar");
        instrumentRepository.save(guitar);

        final Instrument piano = new Instrument();
        piano.setInstrumentId("Piano");
        instrumentRepository.save(piano);

        final Instrument voice = new Instrument();
        voice.setInstrumentId("Voice");
        instrumentRepository.save(voice);

        final Singer singer1 = new Singer();
        singer1.setFirstName("John");
        singer1.setLastName("Mayer");
        singer1.setBirthDate(Date.valueOf("1977-09-16"));
        singer1.addInstruments(guitar, piano);

        final Album album1 = new Album();
        album1.setTitle("The Search For Everything");
        album1.setReleaseDate(Date.valueOf("2017-01-20"));
        final Album album2 = new Album();
        album2.setTitle("Battle Studies");
        album2.setReleaseDate(Date.valueOf("2009-10-17"));
        singer1.addAlbums(album1, album2);
        singerRepository.save(singer1);

        final Singer singer2 = new Singer();
        singer2.setFirstName("Eric");
        singer2.setLastName("Clapton");
        singer2.setBirthDate(Date.valueOf("1945-02-30"));
        singer2.addInstruments(guitar);

        final Album album3 = new Album();
        album3.setTitle("From The Cradle");
        album3.setReleaseDate(Date.valueOf("1994-08-13"));
        singer2.addAlbums(album3);
        singerRepository.save(singer2);

        final Singer singer3 = new Singer();
        singer3.setFirstName("John");
        singer3.setLastName("Butler");
        singer3.setBirthDate(Date.valueOf("1975-03-01"));
        singer3.addInstruments(guitar);
        singerRepository.save(singer3);

        logger.info("Database initialization finished.");
    }
}
