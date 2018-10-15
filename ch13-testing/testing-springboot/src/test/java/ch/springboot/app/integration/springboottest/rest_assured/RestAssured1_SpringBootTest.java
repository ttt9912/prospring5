package ch.springboot.app.integration.springboottest.rest_assured;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;

/*
 * container is started
 *
 * .body(): validate return value
 * .time(): validate response time
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestAssured1_SpringBootTest {

    @LocalServerPort
    private Integer port;

    @Before
    public void setup() {
        RestAssured.port = port;
    }

    @Test
    public void listdata() {
        when().get("/singer/listdata")
                .then().statusCode(200)
                .body("[0].id", equalTo(1))
                .body("[0].firstName", equalTo("John"))
                .body("[1].id", equalTo(2))
                .body("[2].id", equalTo(3));
    }

    @Test
    public void findById() {
        when().get("/singer/1")
                .then().statusCode(200)
                .body("firstName", equalTo("John"))
                .body("instruments", hasItems("Guitar", "Piano"));
    }

    @Test
    public void validateResponseTime() {
        when().get("/singer/listdata")
                .then()
                .time(lessThan(5L), TimeUnit.SECONDS);
    }

}
