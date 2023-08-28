# foreign-memory

Writing and reading "off-heap" memory segments using Java's foreign memory API.


## Overview

Java's [Foreign Function & Memory API](https://openjdk.org/jeps/442) is a new and powerful feature of the platform. It's
a preview feature in Java 21 and is expected to be finalized soon thereafter. I want to learn how to use it.

In this project, we'll build a simple program that writes data (what kind of data should we pick?) into foreign segments
of memory and then searches it and summarizes it. (Maybe just a byte count?)


## Instructions

Follow these instructions to build and run the program.

1. Use Java 17 but have Java 21 installed
   * Java 21 is in preview. It's too new for Gradle to support it. So, use Java 17 to run Gradle tasks but have Java 21
     installed so that Gradle can delegate to Java 21's `javac` and `java` commands for compiling and running our
     program.
2. Build the program distribution:
   * ```shell
     ./gradlew installDist
     ```
3. Switch to Java 21
4. Run the program:
   * ```shell
     build/install/foreign-memory/bin/foreign-memory
     ```


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [x] DONE Scaffold.
* [x] DONE Write to foreign memory.
* [x] DONE Read from foreign memory.
* [ ] Do something more interesting. Can we implement a "glob matcher" program that scans a segment of foreign
  memory? We could read, let's say all `.java` files recursively in this project and match on globs like "stat*" which
  would match "static", "statistics", etc. This is much closer to a real use case. I think this should work, because to
  match on simple globs, we don't need to new up a `String` object, we should be able to just compare the bytes directly.
  Newing up a `String` object would cause tons of allocations which would mostly defeat the purpose of using foreign
  memory. Also, this is more interesting because strings are variable length and we would engage different parts of the
  API.
