# jdk-playground

📚 Learning and exploring tools in the JDK (Java Development Kit).


## Overview

I want to learn about tools in the JDK like `jlink` and `jpackage`. In this repository, I will write working example
projects that use these tools.

---
**NOTE**:

This was developed on macOS and for my own personal use.

---


## Standalone subprojects

This repository illustrates different concepts, patterns and examples via standalone subprojects. Each subproject is
completely independent of the others and do not depend on the root project. This _standalone subproject constraint_
forces the subprojects to be complete and maximizes the reader's chances of successfully running, understanding, and
re-using the code.

The subprojects include:


### `jlink-non-modular/`

This subproject shows how to use [`jlink`](https://openjdk.java.net/jeps/282) on a non-modular Java Gradle project to make a reduced-size JRE image for a lower memory footprint and faster startup!

See the README in [jlink-non-modular/](jlink-non-modular/).


### `jlink-modular/`

Use [`jlink`](https://openjdk.java.net/jeps/282) on a fully-modularized Java Gradle project to make a reduced-size JRE image for a lower memory footprint and faster startup!

See the README in [jlink-modular/](jlink-modular/).


### `single-file-source/`

This subproject explores Java's support for single-file source-code programs introduced in Java 11 via [JEP 330](https://openjdk.java.net/jeps/330).

See the README in [single-file-source/](single-file-source/).


### `process/`

Executing and managing operating system processes from a Java program.

See the README in [process/](process/).


### `annotation-processor/`

An annotation processor that prints basic information about classes as they are visited during the compilation process.

See the README in [annotation-processor/](annotation-processor/).


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [ ] Manage multiple processes in `process/`
* [ ] Find existing processes and terminate them in `process/`
* [ ] IN PROGRESS Create a Java compiler plugin. For example, see the [SemanticDB javac plugin](https://github.com/scalameta/scalameta/blob/613218fce915f10074ed72733c44d7b8cc2432fe/semanticdb/semanticdb3/guide.md?plain=1#L380). 
* [x] DONE Create an annotation processor. This is a lighter weight alternative to a compiler plugin, and should be pretty
  easy.


## Finished Wish List Items

* [x] DONE Implement `jlink-gradle/`
* [x] DONE Scan the classpath for all classes in the "java.*" package to make a more impactful demo of jlink. Use ["classgraph"](https://github.com/classgraph/classgraph)
* [ ] ABANDON Implement `jpackage-gradle/`
* [x] DONE Create a `jlink-modular/` project that is a modularized version of `jlink-non-modular/`
* [x] DONE June 2023 Dependency upgrades. Gradle, Java etc.
* [x] DONE Convert projects to use Gradle version catalog + TOML
* [x] DONE Add a `jextract` subproject. It should call a C function from Java.
* [ ] ABANDON (Java doesn't offer an API for reading heap dumps. You have to reach for third party tools. That's fine but that makes the cost too high for me right now.) Implement a `heap-dump/` subproject. Maybe use VisualVM or maybe just do something headless by reading a heap dump
  from within a Java program and just summarizing the top-level data like instance count. Mainly, I want to learn the
  components of a heap dump and not so much about actual performance analysis.
* [x] DONE Do something with foreign memory using the [Foreign Function & Memory API](https://openjdk.org/jeps/442). Can I
  write a segment of string data to memory and then read it back out? While this is a contrived example, I want to make
  sense of the API and just generally get better at understanding how software interacts with memory (in diverse ways).


## Reference

* [Another project of mine: `gradle-playground`](https://github.com/dgroomes/gradle-playground/tree/main/plugin)
* [Another project of mine: `jshell-playground`](https://github.com/dgroomes/jshell-playground)
