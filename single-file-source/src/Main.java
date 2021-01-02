public class Main {
    public static void main(String[] args) {
        var classPath = System.getProperty("java.class.path");
        System.out.printf("This program is running using java.class.path=%s%n", classPath);
    }
}
