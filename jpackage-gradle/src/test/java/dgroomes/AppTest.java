package dgroomes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void runMain() {
        var message = App.sayHello();
        assertEquals("Hello World! I am running in Java 16-ea", message);
    }
}
