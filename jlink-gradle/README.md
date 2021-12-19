# jlink-gradle

This sub-project shows how to use [`jlink`](https://openjdk.java.net/jeps/282) on a Gradle project to make a reduced-size JRE image (faster startup times and lower memory footprint!).

---
**NOTE**:

This was developed on macOS and for my own personal use.

---

## Design

Using `jlink` requires some understanding of the Java Platform Module System (JPMS). This project in particular is
partially-modularized. What does that mean? Consider that we have no power to modularize library dependencies unless
there already exists a modular version of the library. For example, this project uses SLF4J 1.7.x which is not
modularized. SLF4J has a modularized 1.8.x-beta line of releases and they have not been updated since 2019. I'm not
interested in taking one step forward (modularizing the SLF4J dependency) and two steps back (moving to a beta version
and away from a stable version). So, we will use the non-modular SLF4J 1.7.x release. In that way, this project
is not fully modular.

The way that the project **is modular** is by the `module-info.java` file. This file declares relationships of this
project on other modules like the ClassGraph library which is properly modularized and other libraries like SLF4J 1.7.x
and Jackson 2.12.x which are not modularized. So, again, this project is a mix of modularized and non-modularized
components.

To use `jlink` effectively, we need to know the specific Java modules that the app depends on. For example, if all our
app does is print "Hello World" using Java's logger, then the only module we need is `java.logging`. We can cut
out the vast majority of Java modules!

Most projects will be a mix of modular and non-modular because. But over time, as a project becomes more and more
modularized, it's clearer which modules are needed and which are not, and therefore easier to use `jlink`.

## Instructions

Follow these instructions to build and run the program **without** a custom `jlink`-ed JRE:

1. Use Java 17
1. Run the tests:
   * `./gradlew test`
1. Run the application:
   * `./gradlew run`
   * The output will be something like:
     ```
     [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/.sdkman/candidates/java/17.0.1-tem
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

* Wait for Java 18 is releases and upgrade to it and then upgrade to the latest version of Jackson. There is a defect in
  `jdeps` when I upgrade to Jackson 2.13.x which causes a `com.sun.tools.jdeps.MultiReleaseException`. This issue is
  described in this [StackOverflow answer](https://stackoverflow.com/a/70011064).
* Refactor `MyMessage` to a record.
* IN PROGRESS Modularize the project. 

## Reference

* [Azul blog post: "Using jlink to Build Java Runtimes for non-Modular Applications"](https://medium.com/azulsystems/using-jlink-to-build-java-runtimes-for-non-modular-applications-9568c5e70ef4)
* [Oracle: *Understanding Java 9 Modules*](https://www.oracle.com/corporate/features/understanding-java-9-modules.html)
* [StackOverflow Question: "Using jlink with automatic modules"](https://stackoverflow.com/q/52518105)
* [StackOverflow Answer for Question: "creating module-info for automatic modules with jdeps in java 9"](https://stackoverflow.com/a/47728002)
  * This is a must read.
* [Gradle docs: *Building Java Modules with Legacy Libraries Sample*](https://docs.gradle.org/current/samples/sample_java_modules_with_transform.html) (note: this is the ticket!)
