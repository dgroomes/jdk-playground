# annotation-processor

An annotation processor that prints basic information about classes as they are visited during the compilation process.


## Overview

The Java compiler can be extended with custom "annotation processors" that can be used to do things like generate code.
This is most commonly used in frameworks that generate cross-cutting code like caching, transaction management or by tools
like Lombok that generate boilerplate code.

I'd love to learn the Java annotation processing API so that I can finally grok annotation processors using a real,
runnable example project. Interestingly, you can write an annotation processor that doesn't actually care about
annotations. That's what this project does. It creates a "no-op" annotation processor that just prints out information
about a class like its class name and method names.

---
**NOTE**:

This was developed on macOS and for my own personal use.

---


## Instructions

Follow these instructions to build and run the project.

1. Pre-requisites: Java 21
2. Compile the annotation processor
   * ```shell
     javac -d out/annotation_processor \
       srcAnnotationProcessor/dgroomes/annotation_processor/MyAnnotationProcessor.java
     ```
3. Compile the example program and use the annotation processor
   * ```shell
     javac -d out/time_zone_lister \
       -processor dgroomes.annotation_processor.MyAnnotationProcessor \
       -processorpath out/annotation_processor \
       src/dgroomes/time_zone_lister/TimeZoneLister.java
     ```
   * The output should be something like:
     ```text
     Found class: TimeZoneLister
         Found method: main
         Found method: findTimeZones
     ```
4. If you'd like, run the example program
   * Note: the annotation processor didn't actually have any effect on the example program, but you can run it if you'd
     like. 
   * ```shell
     java -cp out/time_zone_lister dgroomes.time_zone_lister.TimeZoneLister
     ```
   * The output should be something like:
     ```text
     The following time zones were found:
             Etc/GMT+12
             Etc/GMT+11
             Pacific/Midway
             Pacific/Niue
             Pacific/Pago_Pago
             Pacific/Samoa
             US/Samoa
             ... omitted ...
     ```


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [x] DONE Use an actual sample program to be the subject of the annotation processor. It's a bit odd to have the annotation
  processor its own subject.
* [ ] SKIP (no, the direct view of the options is best for learning; plus argument files can't have comments) Consider putting the compiler args in a `javac` command file. Maybe, maybe not.
* [ ] Print fields and types, method params, etc. 


## Reference

* [JavaDocs for `javax.annotation.processing`](https://docs.oracle.com/en/java/javase/21/docs/api/java.compiler/javax/annotation/processing/package-summary.html)
