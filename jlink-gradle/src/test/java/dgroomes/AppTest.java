package dgroomes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void message() {
        var message = new MyMessage("Hello world!");

        assertEquals("Hello world!", message.getMessage());
    }
}
