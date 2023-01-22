# single-file-source

This sub-project explores Java's support for single-file source-code programs introduced in Java 11 via [JEP 330](https://openjdk.java.net/jeps/330).


## Instructions

Follow these instructions to run the single-file source-code program.

1. Use Java 17
2. Compile and run the program with one command!
   * ```shell
     ./hello-world-java
     ```
   * Wow, the instructions were so simple! That's the whole point of single-file source programs! Thank you JEP-330!
   * Altogether, it will look something like this:
     ```text
      $ ./hello-world-java
      Hello world from a single-file source-code Java program, thanks to JEP 330!
	                               Classpath: ''
	                       Current directory: /Users/davidgroomes/repos/personal/jdk-playground/single-file-source
	                             Source path: /Users/davidgroomes/repos/personal/jdk-playground/single-file-source/hello-world-java
	           Program source code directory: /Users/davidgroomes/repos/personal/jdk-playground/single-file-source
     ```

Alternatively, move to a different directory, like the parent directory, and run the program. Notice the different file
paths in the program output:

```text
$ single-file-source/hello-world-java
Hello world from a single-file source-code Java program, thanks to JEP 330!

	                     Classpath: ''
	             Current directory: /Users/davidgroomes/repos/personal/jdk-playground
	                   Source path: /Users/davidgroomes/repos/personal/jdk-playground/single-file-source/hello-world-java
	 Program source code directory: /Users/davidgroomes/repos/personal/jdk-playground/single-file-source
```

Alternatively, symlink the program and run the program via the symlink. If you symlink it to a directory on your `PATH`, 
like `/usr/local/bin`, then you can run the program from anywhere on your system! In the example output below, notice
the "(symbolic link)" and "(real path)" descriptors:

```text
$ ln -sf /Users/davidgroomes/repos/personal/jdk-playground/single-file-source/hello-world-java /usr/local/bin/hello-world-java
$ cd ~
$ hello-world-java
Hello world from a single-file source-code Java program, thanks to JEP 330!

	                     Classpath: ''
	             Current directory: /Users/davidgroomes
	   Source path (symbolic link): /usr/local/bin/hello-world-java
	       Source path (real path): /Users/davidgroomes/repos/personal/jdk-playground/single-file-source/hello-world-java
	 Program source code directory: /Users/davidgroomes/repos/personal/jdk-playground/single-file-source
```


## Wish List

General clean-ups, TODOs and things I wish to implement for this project:

* [x] DONE When running a Java single-file source code program can you find the directory where
  the file is running from, even with symlinks, like you could in Bash?
