# jpackage-gradle

NOT YET IMPLEMENTED

This sub-project shows how to use [`jpackage`](https://openjdk.java.net/jeps/392) on a Gradle project to package it as a self-contained Java application.

## Overview

`jpackage` will be released in Java 16 and so Java 16 is only available in *Early Access* builds. We want to use Java 16 for the project itself but we want to use an earlier version of Java to execute Gradle because Gradle will not officially support Java 16 until it comes out. We can accomplish this with Gradle's [Toolchains for JVM projects](https://docs.gradle.org/current/userguide/toolchains.html). For a more detailed demo of Gradle's "toolchains for JVM projects" feature see <https://github.com/dgroomes/gradle-playground/tree/main/java-toolchain>.

---

### Instructions

1. Use Java 11 or 15
1. Make sure that Java 16 is installed in a location known to Gradle
  * Gradle can [auto-detect installations of Java and the JDK](https://docs.gradle.org/current/userguide/toolchains.html#sec:auto_detection)
  * Alternatively, you can [specify a custom location](https://docs.gradle.org/current/userguide/toolchains.html#sec:custom_loc)  
1. Run the program with `./gradlew run`
1. Run the tests with `./gradlew test`
