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
2. Run the tests:
   * ```shell
     ./gradlew test
     ```
3. Run the application:
   * ```shell
     ./gradlew run
     ```
   * The output will be something like:
     ```text
     18:30:25.652 [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/.sdkman/candidates/java/17.0.5-tem
     18:30:25.723 [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
     18:30:26.004 [main] INFO dgroomes.App - Found 1837 classes in the Java standard library on the classpath in PT0.279508S
     18:30:26.005 [main] INFO dgroomes.App - For example, found 'class java.applet.Applet' and 'class java.applet.Applet$AccessibleApplet'
     ```

Follow these instructions to build a custom reduced-size JRE **with `jlink`** and run the program:

1. Build the program distribution:
   * ```shell
     ./gradlew installDist
     ```
2. Build a custom JRE image:
   * ```shell
     ./build-custom-jre-image-with-jlink.sh
     ```
3. Run the application using the custom JRE:
   * ```shell
     ./run-with-custom-jre-image.sh
     ```
   * The output will be something like:
     ```text
     18:32:47.143 [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/repos/personal/jdk-playground/jlink-modular/build/custom-jre-image
     18:32:47.207 [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
     18:32:47.398 [main] INFO dgroomes.App - Found 1256 classes in the Java standard library on the classpath in PT0.190922S
     18:32:47.399 [main] INFO dgroomes.App - For example, found 'class java.io.BufferedInputStream' and 'class java.io.BufferedOutputStream'
     ```
     Notice how there are hundreds fewer classes than before!


## Reference

* [Oracle: *Understanding Java 9 Modules*](https://www.oracle.com/corporate/features/understanding-java-9-modules.html)
