package dgroomes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws JsonProcessingException {
        var greeting = new MyMessage("Hello world!");

        var json = serialize(greeting);

        log.info("Serialized message: {}", json);
    }

    /**
     * Serialize to JSON
     */
    public static String serialize(MyMessage message) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(message);
    }
}
