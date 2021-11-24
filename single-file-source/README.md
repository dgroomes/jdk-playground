# single-file-source

This sub-project explores Java's support for single-file source-code programs introduced in Java 11 via [JEP-330](https://openjdk.java.net/jeps/330).

## Instructions

1. Use Java 17
2. Compile and run the program with one command!
   * `./main`
   * Wow, the instructions are so simple! That's the whole point of single-file source programs! Thank you JEP-330!
   * The output will be:
     > This program is running using java.class.path=.
     
     This shows an interesting characteristic of single-file Java source-code programs: there is no discernible class path!
     I was expecting the classpath to be the path to a temporary directory containing the compiled source code as `.class`
     files but this is not the case!
