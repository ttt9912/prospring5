package ch99.jacksonjson.p1_json_node;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

public class Demo {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String message = "{ \"name\" : \"Thomas\", \"message\" : \"Hello World\" }";

    @Test
    public void getAttribute() throws IOException {
        JsonNode jsonMessage = objectMapper.readTree(message);
        JsonNode name = jsonMessage.get("name");
        String s = name.textValue();
        System.out.println(s);
    }


}
