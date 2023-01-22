plugins {
    java
    application
}

repositories {
    mavenCentral()
}

val slf4jVersion = "2.0.6" // SLF4J releases: http://www.slf4j.org/news.html
val jacksonVersion = "2.14.1" // Jackson releases: https://github.com/FasterXML/jackson/wiki/Jackson-Releases
val classGraphVersion = "4.8.154" // ClassGraph releases: https://github.com/classgraph/classgraph/releases
val junitVersion = "5.9.2" // JUnit releases: https://junit.org/junit5/docs/current/release-notes/index.html
val assertJVersion = "3.24.2" // AssertJ releases: https://github.com/assertj/assertj-core/tags

dependencies {
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("io.github.classgraph:classgraph:$classGraphVersion")

    testImplementation("org.assertj:assertj-core:$assertJVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
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
