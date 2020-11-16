package dgroomes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws JsonProcessingException {
        var javaHome = System.getProperty("java.home");
        log.info("This program is running using java.home={}", javaHome);

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

    /**
     * NOT YET IMPLEMENTED
     *
     * Find all the classes of the Java standard library. In a custom JRE image built by jlink there will be fewer
     * classes than in a regular distribiption of the JRE! This method can be used to illustrate that.
     *
     * @return a list of the fully qualified class names
     */
    public static List<Class<?>> findAllJavaClasses() {
        return List.of();
    }
}
