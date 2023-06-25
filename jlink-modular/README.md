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
     13:06:34.498 [main] INFO dgroomes.App - This program is running using java.home=/Users/dave/.sdkman/candidates/java/17.0.7-tem
     13:06:34.559 [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
     13:06:34.785 [main] INFO dgroomes.App - Found 1837 classes in the Java standard library on the classpath in PT0.22508S
     13:06:34.785 [main] INFO dgroomes.App - For example, found 'class java.applet.Applet' and 'class java.applet.Applet$AccessibleApplet'
     ```
   * Without modularization, the program would have found even more Java standard library classes. Because the program
     is modularized, the Java runtime did not expose certain classes to the program because they were not required by the
     `module-info.java` file. For example, the classes in `java.net.http` are missing. This is a good thing! It is a
     classic example of the [principle of least privilege](https://en.wikipedia.org/wiki/Principle_of_least_privilege).

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
     13:08:05.060 [main] INFO dgroomes.App - This program is running using java.home=/Users/dave/repos/personal/jdk-playground/jlink-modular/build/custom-jre-image
     13:08:05.114 [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
     13:08:05.280 [main] INFO dgroomes.App - Found 1256 classes in the Java standard library on the classpath in PT0.165495S
     13:08:05.280 [main] INFO dgroomes.App - For example, found 'class java.io.BufferedInputStream' and 'class java.io.BufferedOutputStream'
     ```
   * Notice how there are hundreds fewer classes than before! We are following the principle of least privilege even more
     closely now and the program runs faster and with less memory. `jlink` was able to confidently identify unneeded
     modules and exclude them from the custom JRE image.


## Reference

* [Oracle: *Understanding Java 9 Modules*](https://www.oracle.com/corporate/features/understanding-java-9-modules.html)
