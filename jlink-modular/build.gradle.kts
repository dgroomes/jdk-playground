plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.slf4j.api)

    // Note: I would rather this be a "runtimeOnly" dependency declaration but if I do that then the Java compilation
    // fails with "error: module not found: org.slf4j.simple". This used to work but I'm upgrading across the board to
    // Gradle 8.5 and Java 21, and so I'm not sure what changed.
    implementation(libs.slf4j.simple)

    implementation(libs.jackson.databind)
    implementation(libs.classgraph)

    testImplementation(libs.assertj)
    testImplementation(libs.junit.jupiter.api)
    testRuntimeOnly(libs.junit.jupiter.engine)
}

tasks {
    test {
        useJUnitPlatform()
    }
}

application {
    mainClass.set("dgroomes.App")
    mainModule.set("dgroomes")
}
