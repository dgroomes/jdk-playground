# jlink-gradle

This sub-project shows how to use [`jlink`](https://openjdk.java.net/jeps/282) on a Gradle project to make a reduced-size JRE image (faster startup times and lower memory footprint!).

NOTE: I developed this on macOS.

---

### Instructions

Instructions:

* Use Java 11
* Run the tests with `./gradlew test`
* Run the application with `./gradlew run`
  Output will be something like:
  ```
  [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/.sdkman/candidates/java/11.0.8.hs-adpt
  [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
  ```

Instructions that include a custom JRE:

* Build the program distribution with `./gradlew installDist`
* Build the custom JRE image with `./build-custom-jre-image-with-jlink.sh`
* Run the application using the custom JRE with `./run-with-custom-jre-image.sh`
  Output will be something like:
  ```
  [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/repos/personal/jdk-playground/jlink-gradle/build/custom-jre-image
  [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
  ```

### Materials Referenced

* [Azul blog post: "Using jlink to Build Java Runtimes for non-Modular Applications"](https://medium.com/azulsystems/using-jlink-to-build-java-runtimes-for-non-modular-applications-9568c5e70ef4)

