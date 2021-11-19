# process

Executing and managing operating system processes from a Java program.

---
**NOTE**:

This was developed on macOS and for my own personal use.

---

## Description

I want to learn how to execute other programs from a Java program. Usually, we prefer not to do this and instead favor
using a shell script to execute other programs. Java is generally a poor fit for "scripting" use-cases, however Java's
single-file-source program support and tools like [JBang](https://github.com/jbangdev/jbang) would beg to differ.

Another reason we prefer to not start processes from Java is because it means that we've left the OS independence of the
Java program design and are coding to OS-specific APIs. For example, we can execute `ls -l` from a Java program but only
on Unix-like operating systems. It won't work on Windows. It's better to use the Java standard libraries to list files
in the current directory.

For those reasons I haven't had to learn how to start OS processes from a Java program, but the time has come.

Also, this project doesn't really belong in `jdk-playground` because it explores Java runtime features like the `java.lang.ProcessBuilder`
class and does not explore JDK tooling. But, I didn't want to create yet another `java-` playground.

## Instructions

Follow these instructions to build and run the program.

1. Use Java 17
2. Build the program distribution:
   * `./gradlew installDist`
3. Run the program:
   * `build/install/process/bin/process`
   * Tip: alias the previous two commands into one with the following alias.
   * `alias doit="./gradlew installDist && build/install/process/bin/process"`

## Reference

* [JavaDoc for "java.lang.ProcessBuilder"](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/ProcessBuilder.html)
