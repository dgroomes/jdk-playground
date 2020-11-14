# jlink-gradle

NOT YET IMPLEMENTED

This sub-project shows how to use [`jlink`](https://openjdk.java.net/jeps/282) on a Gradle project to make a reduced-size JRE image (faster startup times and lower memory footprint!).

---

### Instructions

* Use Java 11
* Run the tests with `./gradlew test`
* Run the application with `./gradlew run`
* Build the program distribution with `./gradlew installDist`
* Build the custom JRE image with `./build-jre-image-with-jlink.sh`
* TODO Run the application using the custom JRE with: ?

### Materials Referenced

* [Azul blog post: "Using jlink to Build Java Runtimes for non-Modular Applications"](https://medium.com/azulsystems/using-jlink-to-build-java-runtimes-for-non-modular-applications-9568c5e70ef4)

