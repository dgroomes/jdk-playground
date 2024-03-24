# compiler-plugin

A compiler plugin that implements a naive check for integer overflow.


## Overview

The Java compiler can be extended with [custom plugins](https://docs.oracle.com/en/java/javase/21/docs/api/jdk.compiler/com/sun/source/util/Plugin.html)
that hook into the compilation process of `javac`. Compiler plugins are used by static analysis tools like [Deptective](https://github.com/moditect/deptective).

The Java compiler plugin API was introduced relatively late in Java's history, in Java 8, and so I think many static
analysis tools that pre-date the API are using some other mechanism to parse and analyze Java source code. I'm
interested to know the range of techniques used by static analysis tools in the Java ecosystem:

* How does [SpotBugs](https://github.com/spotbugs/spotbugs) parse Java code?
* How does [OpenRewrite](https://github.com/openrewrite/rewrite) parse Java code?
* How does the [googl-java-format](https://github.com/google/google-java-format/tree/master) parse Java code? 

I want to deepen my understanding of the core JDK toolchain with regard to understanding and manipulating source code,
and I want to broaden my knowledge of these types of tools in the ecosystem (both Open Source and commercial). This
project is me "learning by doing" where I've implemented a simple compiler plugin that checks for integer overflow in
hardcoded integer addition expressions. For example this code is an integer overflow:

```java
int x = 2_147_483_647 + 1; // This is an overflow
```

---
**NOTE**:

This was developed on macOS and for my own personal use.

---


## Instructions

Follow these instructions to build and run the project.

1. Pre-requisites: Java 21
2. Compile the plugin
   * ```shell
     javac -d out/compiler-plugin \
       compiler-plugin/src/dgroomes/compiler_plugin/OverflowDetectorPlugin.java
     ```
3. Compile the example program and use the compiler plugin
   * ```shell
     javac -d out/example-program \
       --processor-path out/compiler-plugin:compiler-plugin/resources \
       -Xplugin:OverflowDetector \
       example-program/src/dgroomes/simple_arithmetic/SimpleArithmetic.java
     ```
   * The output should be something like:
     ```text
     Integer overflow detected at 1463
     ```


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [x] DONE Scaffold the project
* [x] DONE Implement. (Use GPT-4)
* [x] DONE Fix up javac options. I always get so turned around with the classpath, and now doubly so because with a plugin
  there is a "processor" classpath and the service loader framework is involved.
* [ ] Print the line number and source file name instead of whatever number is being printed now.


## Reference

* [JavaDocs for the `com.sun.source.util.Plugin` interface](https://docs.oracle.com/en/java/javase/21/docs/api/java.compiler/javax/annotation/processing/package-summary.html)
