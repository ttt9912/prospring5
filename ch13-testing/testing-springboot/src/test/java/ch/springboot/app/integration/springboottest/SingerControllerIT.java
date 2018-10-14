package ch.springboot.app.integration.springboottest;

import ch.springboot.app.App;
import ch.springboot.data.SingerRepository;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 * nothing is mocked
 *
 * @SpringBootTest: starts the entire container
 *
 * WebEnvironment.MOCK: container will operate in a mock servlet environment
 *
 * @AutoConfigureMockMvc:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK,
        classes = App.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:integration-tests.properties")
public class SingerControllerIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private SingerRepository repository;

    @Test
    public void listdata()
            throws Exception {

        mvc.perform(get("/singer/listdata")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].firstName", Matchers.is("John")));
    }
}
