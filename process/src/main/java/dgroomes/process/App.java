package dgroomes.process;

public class App {

    public static void main(String[] args) throws Exception {
        System.out.println("Executing the following external process from the Java program: 'ls -l'");
        var pb = new ProcessBuilder("ls", "-l");
        pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        pb.redirectErrorStream(true);
        pb.start();
    }
}
