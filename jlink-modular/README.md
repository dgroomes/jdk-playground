# jlink-modular

Use [`jlink`](https://openjdk.java.net/jeps/282) on a fully-modularized Java Gradle project to make a reduced-size JRE image for a lower memory footprint and faster startup!

---
**NOTE**:

See the sibling project `jlink-non-modular/`. It contains lots of contextual information and provides contrast to this
project.

This was developed on macOS and for my own personal use.

---

## Instructions

Follow these instructions to build and run the program the **boring way, without `jlink`**:

1. Use Java 17
1. Run the tests:
   * `./gradlew test`
1. Run the application:
   * `./gradlew run`
   * The output will be something like:
     ```
     00:27:43.967 [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/.sdkman/candidates/java/17.0.1-tem
     00:27:44.114 [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
     00:27:45.255 [main] INFO dgroomes.App - Found 1837 classes in the Java standard library on the classpath in PT1.139861S
     00:27:45.256 [main] INFO dgroomes.App - For example, found 'class java.applet.Applet' and 'class java.applet.Applet$AccessibleApplet'
     ```

Follow these instructions to build a custom reduced-size JRE **with `jlink`** and run the program:

1. Build the program distribution:
   * `./gradlew installDist`
1. Build a custom JRE image:
   * `./build-custom-jre-image-with-jlink.sh`
1. Run the application using the custom JRE:
   * `./run-with-custom-jre-image.sh`
   * The output will be something like:
     ```
     00:28:42.599 [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/repos/personal/jdk-playground/jlink-modular/build/custom-jre-image
     00:28:42.781 [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
     00:28:43.884 [main] INFO dgroomes.App - Found 1256 classes in the Java standard library on the classpath in PT1.100537S
     00:28:43.884 [main] INFO dgroomes.App - For example, found 'class java.io.BufferedInputStream' and 'class java.io.BufferedOutputStream'
     ```
     Notice how there are hundreds fewer classes than before!


## Reference

* [Oracle: *Understanding Java 9 Modules*](https://www.oracle.com/corporate/features/understanding-java-9-modules.html)
