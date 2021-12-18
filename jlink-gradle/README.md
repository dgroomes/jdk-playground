# jlink-gradle

This sub-project shows how to use [`jlink`](https://openjdk.java.net/jeps/282) on a Gradle project to make a reduced-size JRE image (faster startup times and lower memory footprint!).

---
**NOTE**:

This was developed on macOS and for my own personal use.

---

## Instructions

Follow these instructions to build and run the program **without** a custom `jlink`-ed JRE:

1. Use Java 17
1. Run the tests:
   * `./gradlew test`
1. Run the application:
   * `./gradlew run`
   * The output will be something like:
     ```
     [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/.sdkman/candidates/java/11.0.8.hs-adpt
     [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
     ```

Follow these instructions to build and run the program **with** a custom `jlink`-ed JRE:

1. Build the program distribution:
   * `./gradlew installDist`
1. Build a custom JRE image:
   * `./build-custom-jre-image-with-jlink.sh`
1. Run the application using the custom JRE:
   * `./run-with-custom-jre-image.sh`
   * The output will be something like:
     ```
     [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/repos/personal/jdk-playground/jlink-gradle/build/custom-jre-image
     [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
     ```

## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* Upgrade to the latest version of Jackson. For some reason when I upgrade to Jackson 2.13.x I get a `com.sun.tools.jdeps.MultiReleaseException`
  when I run `jdeps`. I'm guessing Jackson 2.13.x is modularized.
* Refactor `MyMessage` to a record.

## Reference

* [Azul blog post: "Using jlink to Build Java Runtimes for non-Modular Applications"](https://medium.com/azulsystems/using-jlink-to-build-java-runtimes-for-non-modular-applications-9568c5e70ef4)

