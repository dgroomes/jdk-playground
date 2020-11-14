package dgroomes;

public class App {

    public static void main(String... args) {
        var message = sayHello();
        System.out.println(message);
    }

    public static String sayHello() {
        var javaVersion = System.getProperty("java.version");
        return String.format("Hello World! I am running in Java %s", javaVersion);
    }
}
