package ch.springboot.app.integration.springboottest.rest_assured;

import ch.springboot.app.App;
import ch.springboot.controller.SingerController;
import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

/*
 * container is mocked by mockmvc
 *
 * In order to start a test using RestAssuredMockMvc you need to
 * initialize it with a either a set of Controllers, a MockMvc instance
 * or a WebApplicationContext from Spring.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = App.class)
@AutoConfigureMockMvc
public class RestAssured2_MockMvc {

    @Before
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(SingerController.class);
    }

    @Test
    public void listdata() {
        when().get("/singer/listdata")
                .then().statusCode(200)
                .body("[0].id", equalTo(1));
    }

}
