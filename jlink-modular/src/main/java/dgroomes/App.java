package dgroomes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.classgraph.ClassGraph;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class App {

    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws JsonProcessingException {
        var javaHome = System.getProperty("java.home");
        log.info("This program is running using java.home={}", javaHome);

        var greeting = new MyMessage("Hello world!");
        var json = serialize(greeting);
        log.info("Serialized message: {}", json);

        var start = Instant.now();
        var allJavaClasses = findAllJavaClasses();
        var end = Instant.now();
        var duration = Duration.between(start, end);
        log.info("Found {} classes in the Java standard library on the classpath in {}", allJavaClasses.size(), duration);
        log.info("For example, found '{}' and '{}'", allJavaClasses.get(0), allJavaClasses.get(1));
    }

    /**
     * Serialize to JSON
     */
    public static String serialize(MyMessage message) throws JsonProcessingException {
        var mapper = new ObjectMapper();
        return mapper.writeValueAsString(message);
    }

    /**
     * Find all the classes of the Java standard library. In a custom JRE image built by jlink there will be fewer
     * classes than in a regular distribution of the JRE! This method can be used to illustrate that.
     * <p>
     * NOTE: this method will also cause all the found classes to be loaded by the JVM.
     *
     * @return a list of the fully qualified class names
     */
    public static List<Class<?>> findAllJavaClasses() {
        var scanResult = new ClassGraph().acceptPackages("java").enableSystemJarsAndModules().scan();
        var classInfoList = scanResult.getAllClasses();
        return classInfoList.loadClasses();
    }
}
