package ch.springboot.app.integration.springboottest.rest_assured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

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

    @Test
    public void withJsonPath() {

        Response response = RestAssured.get("/singer/1");

        assertThat(response.getStatusCode(), equalTo(200));

        JsonPath jsonPath = response.jsonPath();
        System.out.println(jsonPath.prettyPrint());

        assertThat(jsonPath.get("firstName"), equalTo("John"));
        assertThat(jsonPath.getList("instruments"), hasItems("Guitar", "Piano"));
    }

    @Test
    public void synthax_stuff() {

        // DSL
        RequestSpecification given = RestAssured.given();
        RequestSender when = RestAssured.when();

        // HTTP verbs
        Response response = RestAssured.get("/api");
        Response post = RestAssured.post("/api");

        // Response: get response values
        String json = response.asString();
        ResponseBody body = response.body();

        // ValidatableResponse: evaluate with jsonpath & hamcrest
        ValidatableResponse then = response.then();

        ValidatableResponse eval = then.body("id", equalTo(1));
        ValidatableResponse assertThat = then.assertThat();
        ValidatableResponse contentType = then.contentType(ContentType.JSON);

        // synhactic sugar
        ValidatableResponse and = eval.and();

    }

}
