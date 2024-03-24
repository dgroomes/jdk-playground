plugins {
    java
    application
}

/*
  Warning: This Gradle build file only exists to ease the developer experience when opening this project in an IDE. See the
  extensive note in the file `settings.gradle.kts` for more information. This Gradle file is not meant to be used as an
  example.

  This build file will define the subproject `single-file-source/` as a Gradle project so that Intellij will
  automatically recognize the source code provide a working "click the green play button to execute the program" experience.
  But, importantly, this subproject still works as a standalone project without Gradle.

  A good test for validating that this Gradle configuration works is to actually execute the main methods. Try it with:
    * `./gradlew single-file-source:run`

  A better test is to open this project in Intellij, wait for Intellij to make sense of the project, and then click the
  green play buttons in the editor gutter on the "public static void main" method. The program should run.
 */

project("single-file-source") {

    apply(plugin = "java")
    apply(plugin = "application")

    repositories {
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(17))
        }
    }

    sourceSets {
        main {
            java {
                setSrcDirs(listOf("src"))
            }
        }
    }

    application {
        mainClass.set("Main")
    }
}

/*
The same is true for the 'annotation-processor' subproject.
*/
project("annotation-processor") {

    apply(plugin = "java")
    apply(plugin = "application")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    sourceSets {
        main {
            java {
                setSrcDirs(listOf("src", "srcAnnotationProcessor"))
            }
        }
    }
}

/*
The same is true for the 'compiler-plugin' subproject.
*/
project("compiler-plugin") {

    apply(plugin = "java")
    apply(plugin = "application")

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    }

    sourceSets {
        main {
            java {
                setSrcDirs(listOf("src", "srcCompilerPlugin"))
            }
        }
    }
}

