plugins {
    kotlin("jvm") version "2.4.20"
    `java-library`
}

group = "io.github.necdetzr"
version = "0.1.0"
description = "Turkish dative suffixes for proper names in Kotlin/JVM."

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.2"))
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    jvmToolchain(17)
}

java {
    withSourcesJar()
}

tasks.test {
    useJUnitPlatform()
}
