package ch.springboot.app.unit_tests;

import ch.springboot.data.Singer;
import ch.springboot.data.SingerRepository;
import ch.springboot.service.SingerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/*
 * Test the Service layer, mock the Repository
 *
 * @MockBean: spring support for Mockito
 *
 * @TestConfiguration: beans defined inside should only
 * be created for test and not picked up by other component scannings
 *
 * @MockBean: creates a mock
 */
@RunWith(SpringRunner.class)
public class SingerServiceTest {

    @TestConfiguration
    static class SingerServiceTestConfig {
        @Bean
        public SingerService singerService() {
            return new SingerService();
        }
    }

    @Autowired
    private SingerService singerService;

    @MockBean
    private SingerRepository singerRepository;

    @Before
    public void setUp() {
        final Singer singer = new Singer();
        singer.setFirstName("Paul");
        singer.setLastName("McCartney");

        when(singerRepository.findByFirstName("Paul"))
                .thenReturn(Collections.singletonList(singer));
    }

    @Test
    public void findByName() {
        List<Singer> singers = singerService.findByFirstName("Paul");

        assertThat(singers).hasSize(1);
        assertThat(singers.get(0).getFirstName()).isEqualTo("Paul");
    }
}
