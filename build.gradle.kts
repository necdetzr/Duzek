plugins {
    kotlin("jvm") version "2.4.20"
}

group = "org.example"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")

}

kotlin {
    jvmToolchain(17)
}

tasks.test {
    useJUnitPlatform()
}