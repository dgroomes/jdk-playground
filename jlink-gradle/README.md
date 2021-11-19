# jlink-gradle

THIS BRANCH IS BROKEN. I'm trying to add ClassGraph. And the program runs for the normal JRE but for the jlink'ed JRE
it throws `Caused by: java.lang.ClassNotFoundException: sun.misc.Unsafe`. This is because I don't understand the Java Module
system. I need to make an example project that explores a Gradle project that uses the Java module system and how to deal
with the module descriptor data (i.e. `module-info.java` and such) so that the `jdeps` step can actually realize that ClassGraph
depends on [`jdk.unsupported`](https://github.com/classgraph/classgraph/blob/905edfc660948aee1b0b2b7d6e240e402c33d319/src/module-info/io.github.classgraph/module-info.java#L42)

This sub-project shows how to use [`jlink`](https://openjdk.java.net/jeps/282) on a non-modular Java Gradle project to make a reduced-size JRE image (faster startup times and lower memory footprint!).

Note: this was developed on macOS.

## Instructions

Follow these instructions to build and run the program the *boring way, without jlink*:

* Use Java 11
* Run the tests with `./gradlew test`
* Run the application with `./gradlew run`
  Output will be something like:
  ```
  [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/.sdkman/candidates/java/11.0.10.hs-adpt
  [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
  [main] INFO dgroomes.App - Found 1904 classes in the Java standard library on the classpath in PT1.368796S
  [main] INFO dgroomes.App - For example, found 'class java.applet.Applet' and 'class java.applet.Applet$AccessibleApplet'
  ```

Follow these instructions to build a custom reduced-size JRE and the run the program using it:

* Build the program distribution and custom JRE image:
  * `./build-custom-jre-image-with-jlink.sh`
* Run the application using the custom JRE:
  * `./run-with-custom-jre-image.sh`
* The output will be something like:
  ```
  [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/repos/personal/jdk-playground/jlink-gradle/build/custom-jre-image
  [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
  ```

## Java Platform Module System

There is lots of good information about the Java Platform Module System (JPMS) and the other associated features of Java 9
like `jlink` because it has now been over three years since the release of Java 9! Blog posts, StackOverflow questions and answers
and other information have accumulated over this time to describe facets of Java 9 and how to use it. I personally am only now
getting into the JPMS stuff and I think it's pretty cool and I also think it's a bit complicated. This sub-project tries to
show how you might take the `jlink` tool to make a custom reduced-size JRE image for a prototypical Java/Gradle project. And by prototypical
I mean *non-modular*! Most Java projects out in the wild are still non-modular and even most new projects remain non-modular.
Was modularization a failed experiment for user code? Is it only a useful feature for the Java platform itself? I don't know,
but I'm still going to learn... After trial and error, I've found that modularizing an application to use the JPMS features is especially
difficult because you have no control of the many third-party Java dependencies you might be using and therefore you have
to just stick with the fact that many of them have also not been modularized!

So, while this project shows how to use `jlink` on a non-modular project, my findings are that I might recommend that you
focus on getting your app modularized before getting into `jlink`... An already modularized app makes for a much more idiomatic
usage of `jlink` than a non-modularized application. A natural follow up recommendation is to consider that it might not be
feasible to modularize your app; you might want to wait a few more years.

### Guesstimating a program's modules

See the note in `guesstimate-modules.sh`. This script is a simple runner for `jdeps` and it outputs an incomplete list of
modules that the program depends on. In practice, you cannot rely on it to know what modules the program uses. You must do research.
For example, the ClassGraph dependency requires the [`jdk.upsupported` module](https://github.com/classgraph/classgraph/blob/905edfc660948aee1b0b2b7d6e240e402c33d319/src/module-info/io.github.classgraph/module-info.java#L42).
You must manually maintain the list of required modules in the `modules.txt` file which is referenced by `build-custom-jre-image-with-jlink.sh`.

Some working notes about wrangling with the JPMS and Gradle (I'm a newbie at JPMS):

### Materials Referenced

* [Azul blog post: "Using jlink to Build Java Runtimes for non-Modular Applications"](https://medium.com/azulsystems/using-jlink-to-build-java-runtimes-for-non-modular-applications-9568c5e70ef4)

