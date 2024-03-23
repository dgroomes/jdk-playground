# annotation-processor

An annotation processor that prints basic information about classes as they are visited during the compilation process.


## Overview

The Java compiler can be extended with custom "annotation processors" that can be used to do things like generate code.
I'd love to learn the API for writing annotation processors. Interestingly, you can write an annotation processor that
doesn't actually care about annotations. That's what this project does.

---
**NOTE**:

This was developed on macOS and for my own personal use.

---


## Instructions

Follow these instructions to build and run the project.

1. Pre-requisites: Java 21
2. Compile the annotation processor
   * ```shell
     javac -d out/ src/dgroomes/MyAnnotationProcessor.java
     ```
3. Re-compile the annotation processor but **using the annotation processor**
   * ```shell
     javac -d out/ -processor dgroomes.MyAnnotationProcessor -processorpath out/ src/dgroomes/MyAnnotationProcessor.java
     ```
   * The output should be something like:
     ```text
     Found class: MyAnnotationProcessor
         Found method: process
     ```


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [ ] Use an actual sample program to be the subject of the annotation processor. It's a bit odd to have the annotation
  processor its own subject.
* [ ] Consider putting the compiler args in a `javac` command file. Maybe, maybe not.
* [ ] Print fields and types, method params, etc. 


## Reference

* [JavaDocs for `javax.annotation.processing`](https://docs.oracle.com/en/java/javase/21/docs/api/java.compiler/javax/annotation/processing/package-summary.html)
