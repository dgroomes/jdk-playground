// This file and the full contents of 'buildSrc/' was taken from the excellent example by Gradle: https://docs.gradle.org/current/samples/sample_java_modules_with_transform.html

plugins {
    `java-gradle-plugin`
}

dependencies {
    implementation("org.ow2.asm:asm:8.0.1")
}

repositories {
    mavenCentral()
}

gradlePlugin {
    plugins {
        // here we register our plugin with an ID
        register("extra-java-module-info") {
            id = "extra-java-module-info"
            implementationClass = "org.gradle.sample.transform.javamodules.ExtraModuleInfoPlugin"
        }
    }
}
