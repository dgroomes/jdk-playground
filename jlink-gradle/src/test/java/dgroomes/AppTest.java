package dgroomes;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AppTest {

    String expectedJson = "{\"message\":\"Hello world!\"}";

    @Test
    void message() throws JsonProcessingException {
        var greeting = new MyMessage("Hello world!");

        var serialized = App.serialize(greeting);

        assertThat(serialized).isEqualTo(expectedJson);
    }

    @Disabled("Not yet implemented")
    @Test
    void findAllJavaClasses() {
        var allJavaClasses = App.findAllJavaClasses();

        assertThat(allJavaClasses).size().isGreaterThan(0);
    }
}
