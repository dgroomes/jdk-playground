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

See [jlink-gradle/README.md](jlink-gradle/README.md).

### `jpackage-gradle/`

NOT YET IMPLEMENTED

This sub-project shows how to use [`jpackage`](https://openjdk.java.net/jeps/392) on a Gradle project to package it as a self-contained Java application.

See [jpackage-gradle/README.md](jpackage-gradle/README.md).


## WishList

General clean-ups, TODOs and things I wish to implement for this project:

* Implement `jlink-gradle/`
* Implement `jpackage-gradle/`