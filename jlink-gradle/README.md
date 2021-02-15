# jlink-gradle

THIS BRANCH IS BROKEN. I'm trying to add ClassGraph. And the program runs for the normal JRE but for the jlink'ed JRE
it throws `Caused by: java.lang.ClassNotFoundException: sun.misc.Unsafe`. This is because I don't understand the Java Module
system. I need to make an example project that explores a Gradle project that uses the Java module system and how to deal
with the module descriptor data (i.e. `module-info.java` and such) so that the `jdeps` step can actually realize that ClassGraph
depends on [`jdk.unsupported`](https://github.com/classgraph/classgraph/blob/905edfc660948aee1b0b2b7d6e240e402c33d319/src/module-info/io.github.classgraph/module-info.java#L42)

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

* Build the program distribution and custom JRE image:
  * `./build-custom-jre-image-with-jlink.sh`
* Run the application using the custom JRE:
  * `./run-with-custom-jre-image.sh`
* The output will be something like:
  ```
  [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/repos/personal/jdk-playground/jlink-gradle/build/custom-jre-image
  [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
  ```
  

### Java Platform Module System

Some working notes about wrangling with the JPMS and Gradle (I'm a newbie at JPMS):

* `jdeps --generate-module-info . build/install/jlink-gradle/lib/slf4j-simple-1.7.30.jar build/install/jlink-gradle/lib/slf4j-api-1.7.30.jar`
* From StackOverflow `javac --patch-module <module name>=<path to jar> module-info.java`
* Doesn't work `javac --patch-module org.slf4j=build/install/jlink-gradle/lib/slf4j-api-1.7.30.jar org.slf4j/module-info.java`
* (two deps) Doesn't work `javac --patch-module org.slf4j=build/install/jlink-gradle/lib/slf4j-api-1.7.30.jar org.slf4j/module-info.java --patch-module org.slf4j.simple=build/install/jlink-gradle/lib/slf4j-simple-1.7.30.jar org.slf4j.simple/module-info.java`
* ? `javac --patch-module org.slf4j.simple=build/install/jlink-gradle/lib/slf4j-simple-1.7.30.jar org.slf4j.simple/module-info.java`

### Materials Referenced

* [Azul blog post: "Using jlink to Build Java Runtimes for non-Modular Applications"](https://medium.com/azulsystems/using-jlink-to-build-java-runtimes-for-non-modular-applications-9568c5e70ef4)
* [StackOverflow Q&A: *How to inject module declaration into JAR?*](https://stackoverflow.com/questions/47222226/how-to-inject-module-declaration-into-jar)
  * The advice in this StackOverflow question and answer is needed for patching Java dependencies that are non-modularized (JPMS)
    for working with `jlink`.
