# jextract

Call from Java code into C code using the `jextract` tool.

> jextract is a tool which mechanically generates Java bindings from a native library headers.
> 
> -- <cite> https://github.com/openjdk/jextract </cite>


## Overview

I want a Java program that can call into a native library written in C. I've wanted to do this for a long time and `jextract`
has had a few years to mature so now is a good time to try it out.

[Apache Lucene is using](https://github.com/apache/lucene/commit/3b9c728ab558255eb8329a017b9d235611d7b142) Java's exciting
[Foreign Function & Memory API](https://openjdk.org/jeps/442) which was delivered as a preview in Java 19. This is cool.
I want to to use `jextract` to generate Java bindings for a native library and then use the Foreign Function API to call
the library from Java code.


## Instructions

Follow these instructions to build the C library, the Java program and run the Java program.

1. Use Java 20
2. Use a matching `jextract` distribution
   * [Download one from OpenJDK](https://jdk.java.net/jextract/). 
3. Compile the C library
   * ```shell
     clang -dynamiclib -o lucky_number.dylib lucky_number.c
     ```
4. Extract the Java bindings
   * ```shell
     jextract --source \
      --output src/main/java \
      --target-package dgroomes.bindings \
      -I "$PWD" \
      -l "$PWD/lucky_number.dylib" \
     lucky_number.h
     ```
   * Glance over the generated code. Specifically, notice how the `RuntimeHelper` class hard codes to the fully qualified
     path of the dynamic shared library to load it. 
   * ```java
     final class RuntimeHelper {
       // ... omitted ...
       static {
          System.load("/Users/davidgroomes/repos/personal/jdk-playground/jextract/lucky_number.dylib");
          SymbolLookup loaderLookup = SymbolLookup.loaderLookup();
          SYMBOL_LOOKUP = name -> loaderLookup.lookup(name).or(() -> LINKER.defaultLookup().lookup(name));
       }
       // ... omitted ...
     }
     ```
   * The path will be different on your machine. This makes the generated code not portable. But for this project, we
     don't have a need for portability.
5. Compile the Java program
   * ```shell
     ./gradlew installDist
     ```
6. Run the Java program
   * ```shell
     build/install/jextract/bin/jextract
     ```
   * It should look something like this:
     ```text
     $ build/install/jextract/bin/jextract
     Let's call native code from Java! Here we go...
     Your lucky number is 123.
     ```


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [ ] Engage foreign memory. Do something with C strings, structs, and/or pointers.
* [ ] Modularize the program (idiomatic). I was having some Gradle issue when I tried. I think it's a Gradle toolchain
  defect.
* [ ] Consider baking the dylib into the program distribution. Not a big deal.


## Reference

* [GitHub repo: `openjdk/jextract`](https://github.com/openjdk/jextract)
* [GitHub repo: `carldea/panama4newbies`](https://github.com/carldea/panama4newbies)
  * This is exactly what I'm trying to learn. Wow, thanks for the great content Carl! This is the accompaniment repo to
    the Foojay articles written by Carl.
* [foojay.io: *Project Panama for Newbies (Part 1)*](https://foojay.io/today/project-panama-for-newbies-part-1/)
* [foojay.io: *Building Project Panama’s jextract tool by yourself*](https://foojay.io/today/building-project-panamas-jextract-tool-by-yourself/)
  * I might need to build `jextract` from source. Not sure yet.
* [foojay.io: *Java Panama Polyglot (C++) Part 1*](https://foojay.io/today/java-panama-polyglot-part1/)
  * Great example.
