package ch.springboot.app.integration.data_jpa_test;

import ch.springboot.app.AppConfig;
import ch.springboot.data.Singer;
import ch.springboot.data.SingerRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * @RunWith(SpringRunner.class): provides a bridge between Spring Boot
 *                               test features and JUnit
 *
 * @DataJpaTest: provides some standard setup (H2, hibernate, etc.)
 *
 * Libraries used: Junit4 (SpringRunner requires junit4) & AssertJ
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {AppConfig.class})
public class SingerRepositoryIT {

    @Autowired
    private TestEntityManager tem; // setting up test data

    @Autowired
    private SingerRepository singerRepository;

    @Before
    public void insertTestData() {
        final Singer singer = new Singer();
        singer.setFirstName("Paul");
        singer.setLastName("McCartney");
        tem.persist(singer);
        tem.flush();
    }

    @Test
    public void whenFindByName_thenReturnSinger() {
        List<Singer> result = singerRepository.findByFirstName("Paul");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getFirstName()).isEqualTo("Paul");
    }
}


