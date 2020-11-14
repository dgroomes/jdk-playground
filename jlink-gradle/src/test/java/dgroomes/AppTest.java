package dgroomes;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    String expectedJson = "{\"message\":\"Hello world!\"}";

    @Test
    void message() throws JsonProcessingException {
        var greeting = new MyMessage("Hello world!");

        var serialized = App.serialize(greeting);

        assertEquals(expectedJson, serialized);
    }
}
