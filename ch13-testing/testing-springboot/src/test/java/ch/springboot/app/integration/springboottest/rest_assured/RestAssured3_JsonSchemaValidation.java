package ch.springboot.app.integration.springboottest.rest_assured;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/*
 * compare the result of a request with the content of a file.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAssured3_JsonSchemaValidation {

    @LocalServerPort
    private Integer port;

    @Before
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void findById() {
        when().get("/singer/1")
                .then().assertThat()
                .body(matchesJsonSchemaInClasspath("singer1.json"));
    }
}
