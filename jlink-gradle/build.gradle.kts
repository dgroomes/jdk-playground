plugins {
    application
    id("extra-java-module-info") // Apply the plugin defined in 'buildSrc/'
}

repositories {
    mavenCentral()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

val slf4jVersion = "1.7.32" // SLF4J releases: http://www.slf4j.org/news.html
val jacksonVersion = "2.12.6" // Jackson releases: https://github.com/FasterXML/jackson/wiki/Jackson-Releases
val junitVersion = "5.8.2" // JUnit releases: https://junit.org/junit5/docs/current/release-notes/index.html
val classGraphVersion = "4.8.138" // ClassGraph releases: https://github.com/classgraph/classgraph/releases
val assertJVersion = "3.21.0" // AssertJ releases: https://github.com/assertj/assertj-core/tags

dependencies {
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("io.github.classgraph:classgraph:$classGraphVersion")

    testImplementation("org.assertj:assertj-core:$assertJVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

extraJavaModuleInfo {
    // Modularize the SLF4J API library. The below configuration is directly modeled after SLF4J's own 1.8.x beta line
    // which modularizes the library. See https://github.com/qos-ch/slf4j/blob/c7e0311517153d72e6edcf0ab52be7fb42ea36a7/slf4j-api/src/main/java9/module-info.java#L1
    module("slf4j-api-$slf4jVersion.jar", "org.slf4j", slf4jVersion) {
        exports("org.slf4j")
        exports("org.slf4j.spi")
        exports("org.slf4j.event")
        exports("org.slf4j.helpers")
        uses("org.slf4j.spi.SLF4JServiceProvider")
    }

    // Similarly, modularize the SLF4J Simple logging implementation.
    // See https://github.com/qos-ch/slf4j/blob/v_1.8.0-beta4/slf4j-simple/src/main/java9/module-info.java#L1
    module("slf4j-simple-$slf4jVersion.jar", "org.slf4j.impl", slf4jVersion) {
        requires("org.slf4j")
        provides("org.slf4j.spi.SLF4JServiceProvider", "org.slf4j.simple.SimpleServiceProvider")
    }
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
