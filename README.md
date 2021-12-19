# jdk-playground

📚 Learning and exploring tools in the JDK (Java Development Kit).

## Overview

I want to learn about tools in the JDK like `jlink` and `jpackage`. In this repository, I will write working example
projects that use these tools.

## Standalone sub-projects

This repository illustrates different concepts, patterns and examples via standalone sub-projects. Each sub-project is
completely independent of the others and do not depend on the root project. This _standalone sub-project constraint_
forces the sub-projects to be complete and maximizes the reader's chances of successfully running, understanding, and
re-using the code.

The sub-projects include:

### `jlink-gradle/`

This sub-project shows how to use [`jlink`](https://openjdk.java.net/jeps/282) on a Gradle project to make a reduced-size JRE image (faster startup times and lower memory footprint!).

See the README in [jlink-gradle/](jlink-gradle/).

### `jpackage-gradle/`

NOT YET IMPLEMENTED

This sub-project shows how to use [`jpackage`](https://openjdk.java.net/jeps/392) on a Gradle project to package it as a self-contained Java application.

See the README in [jpackage-gradle/](jpackage-gradle/).

### `single-file-source/`

This sub-project explores Java's support for single-file source-code programs introduced in Java 11 via [JEP 330](https://openjdk.java.net/jeps/330).

See the README in [single-file-source/](single-file-source/).

### `process/`

Executing and managing operating system processes from a Java program.

See the README in [process/](process/).

## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* DONE Implement `jlink-gradle/`
* IN PROGRESS Scan the classpath for all classes in the "java.*" package to make a more impactful demo of jlink. Use ["classgraph"](https://github.com/classgraph/classgraph)
* Implement `jpackage-gradle/`
* Manage multiple processes in `process/`
* Find existing processes and terminate them in `process/`

## Referenced materials

* [Another project of mine: `gradle-playground`](https://github.com/dgroomes/gradle-playground/tree/main/plugin)
* [Another project of mine: `jshell-playground`](https://github.com/dgroomes/jshell-playground)
* [ASM](https://asm.ow2.io/index.html)
  > ASM is an all purpose Java bytecode manipulation and analysis framework. 
