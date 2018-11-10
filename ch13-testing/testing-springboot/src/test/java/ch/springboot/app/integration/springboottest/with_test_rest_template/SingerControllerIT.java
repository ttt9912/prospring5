package ch.springboot.app.integration.springboottest.with_test_rest_template;

import ch.springboot.data.Singer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * Starts server on a random port (WebEnvironment.RANDOM_PORT)
 * and injects port into @LocalServerPort variable
 *
 * TestRestTemplate: provided by spring boot automatically
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SingerControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void listdata() throws Exception {
        assertThat(restTemplate.getForObject("http://localhost:" + port + "/singer/1", Singer.class))
                .extracting(Singer::getFirstName).isSameAs("John");
    }
}
