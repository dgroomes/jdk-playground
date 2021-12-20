package dgroomes;

import java.util.Objects;

/**
 * A simple POJO class
 */
public class MyMessage {
    private final String message;

    public MyMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MyMessage{" +
                "message='" + message + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyMessage myMessage = (MyMessage) o;
        return Objects.equals(message, myMessage.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }
}
