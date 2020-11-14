plugins {
    java
    application
}

repositories {
    mavenLocal()
    jcenter()
}

val slf4jVersion = "1.7.30" // releases: http://www.slf4j.org/news.html
val jacksonVersion = "2.11.3" // releases: https://github.com/FasterXML/jackson/wiki/Jackson-Releases
val junitVersion = "5.7.0" // releases: https://junit.org/junit5/docs/current/release-notes/index.html

dependencies {
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    implementation("org.slf4j:slf4j-simple:$slf4jVersion")
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")

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
