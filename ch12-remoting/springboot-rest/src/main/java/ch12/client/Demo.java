package ch12.client;

import ch12.springboot.rest.data.Singer;
import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*
 * RestTemplate does not need any configuration
 */
public class Demo {

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void singerById() {
        System.out.println("\n--- Singer for id=1 ---");
        Singer singer = restTemplate.getForObject("http://localhost:8080/singer/1", Singer.class);
        System.out.println(singer);
    }

    @Test
    public void allSingers() {
        System.out.println("\n--- Singers ---");

        ResponseEntity<List<Singer>> responseEntity = restTemplate.exchange(
                "http://localhost:8080/singer/listdata",
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Singer>>() {
                });
        responseEntity.getBody().forEach(System.out::println);
    }

}
