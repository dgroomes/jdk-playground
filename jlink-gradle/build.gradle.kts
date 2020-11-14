plugins {
    java
    application
}

repositories {
    mavenLocal()
    jcenter()
}

val junitVersion = "5.7.0" // releases: https://junit.org/junit5/docs/current/release-notes/index.html

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks {

    withType(Test::class.java) {
        useJUnitPlatform()
    }
}

application {
    mainClass.set("dgroomes.App")
}
