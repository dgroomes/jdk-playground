plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.slf4j.api)
    runtimeOnly(libs.slf4j.simple)
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
