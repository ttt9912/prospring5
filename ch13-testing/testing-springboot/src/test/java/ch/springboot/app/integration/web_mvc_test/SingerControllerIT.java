package ch.springboot.app.integration.web_mvc_test;

import ch.springboot.controller.SingerController;
import ch.springboot.data.Singer;
import ch.springboot.service.SingerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
 * only Controller code is tested, Service layer is mocked
 *
 * @WebMvcTest: auto-configures the Spring MVC infrastructure and
 *              mocks a http server
 *
 * MockMvc: testing MVC controllers without starting a full HTTP server
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SingerController.class)
public class SingerControllerIT {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private SingerService singerService;

    @Test
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {

        final Singer singer = new Singer();
        singer.setFirstName("Paul");
        singer.setLastName("McCartney");
        List<Singer> singers = Collections.singletonList(singer);

        when(singerService.findAll()).thenReturn(singers);

        mvc.perform(get("/singer/listdata")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(singer.getFirstName())));
    }
}
