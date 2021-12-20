# jlink-non-modular

This sub-project shows how to use [`jlink`](https://openjdk.java.net/jeps/282) on a non-modular Java Gradle project to make a reduced-size JRE image for a lower memory footprint and faster startup!

---
**NOTE**:

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
     21:53:25.594 [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/.sdkman/candidates/java/17.0.1-tem
     21:53:25.803 [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
     21:53:27.055 [main] INFO dgroomes.App - Found 1920 classes in the Java standard library on the classpath in PT1.250711S
     21:53:27.056 [main] INFO dgroomes.App - For example, found 'class java.applet.Applet' and 'class java.applet.Applet$AccessibleApplet'
     ```

Follow these instructions to build a custom reduced-size JRE **with `jlink`** and run the program:

1. Build the program distribution:
   * `./gradlew installDist`
1. Make an educated guess about the Java modules:
   * `./guesstimate-modules.sh`
   * Read the detailed note in that shell script to learn why this is necessary.
   * Read the output of the command and update the `modules.txt` if needed. This is necessary whenever a new library
     dependency is added. Does the dependency depend on a module that hasn't already been listed in `modules.txt`? You
     must add it to `modules.txt`. By contrast if you upgrade a dependency or delete a dependency, which modules are no
     longer needed? You must delete them from `modules.txt`.
1. List the explicitly declared module dependencies:
   * `./list-explicit-modules.sh`
   * Read the note in that shell script to learn why this is necessary.
   * Read the output of the command and update the `modules.txt` if needed.
   * Remember, it is up to the developer to make the final decision about what modules are included and which are
     omitted. For example, the `jackson-databind` library declares a `requires static` relationship to `java.desktop`,
     `java.sql`, and `java.xml`. `static` dependencies are optional at runtime. We know that we happen to not need them
     in our program so it's best to omit them.
1. Build a custom JRE image:
   * `./build-custom-jre-image-with-jlink.sh`
1. Run the application using the custom JRE:
   * `./run-with-custom-jre-image.sh`
   * The output will be something like:
     ```
     23:10:25.708 [main] INFO dgroomes.App - This program is running using java.home=/Users/davidgroomes/repos/personal/jdk-playground/jlink-non-modular/build/custom-jre-image
     23:10:25.919 [main] INFO dgroomes.App - Serialized message: {"message":"Hello world!"}
     23:10:26.904 [main] INFO dgroomes.App - Found 1256 classes in the Java standard library on the classpath in PT0.982713S
     23:10:26.905 [main] INFO dgroomes.App - For example, found 'class java.io.BufferedInputStream' and 'class java.io.BufferedOutputStream'
     ```
     Notice how there are hundreds fewer classes than before! The execution time was a bit faster too, but not by much.
     A more extensive test must be done if you're interested in performance improvements.

## Java Platform Module System

There is lots of good information about the Java Platform Module System (JPMS) and the other associated features of Java 9
like `jlink` because it has now been over four years since the release of Java 9! Blog posts, StackOverflow questions
and answers and other information have accumulated over this time to describe facets of Java 9 and how to use it. I
personally am only now getting into the JPMS stuff and I think it's pretty cool and I also think it's a bit complicated.
This sub-project tries to show how you might take the `jlink` tool to make a custom reduced-size JRE image for a
prototypical Java/Gradle project. And by prototypical I mean *non-modular*! Most Java projects out in the wild are still
non-modular and even most new projects remain non-modular. Was modularization a failed experiment for user code? Is it
only a useful feature for the Java platform itself? I don't know, but I've learned some things about it.

After trial and error, I've found that modularizing an application to use the JPMS features is especially
difficult because you have no control of the many third-party Java dependencies you might be using and therefore you have
to just stick with the fact that many of them have also not been modularized!

So, while this project shows how to use `jlink` on a non-modular project, my findings are that I might recommend that you
focus on getting your app modularized before getting into `jlink`... An already modularized app makes for a much more idiomatic
usage of `jlink` than a non-modularized application. A natural follow up recommendation is to consider that it might not be
feasible to modularize your app; you might want to wait a few more years.

## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* Wait for Java 18 is releases and upgrade to it and then upgrade to the latest version of Jackson. There is a defect in
  `jdeps` when I upgrade to Jackson 2.13.x which causes a `com.sun.tools.jdeps.MultiReleaseException`. This issue is
  described in this [StackOverflow answer](https://stackoverflow.com/a/70011064).
* Refactor `MyMessage` to a record.
* DONE Incorporate the `guesstimate-modules.sh` from the `abandoned-` branches and include explanation about why the developer
  needs to make a manual judgement about the necessary modules and we can't rely on `jdeps` (I have tried many
  combinations of `jdeps` command incantations and none will give the a single: "These are all the modules" output. Some
  incantations give me the modules detected by via the use of class names, and some inspect specific modules but none
  gives the whole view).

## Reference

* [Azul blog post: "Using jlink to Build Java Runtimes for non-Modular Applications"](https://medium.com/azulsystems/using-jlink-to-build-java-runtimes-for-non-modular-applications-9568c5e70ef4)

