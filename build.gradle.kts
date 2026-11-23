plugins {
    kotlin("jvm") version "2.4.20"
    `java-library`
    `maven-publish`
    signing
    id("org.jetbrains.dokka") version "2.2.0"
    id("com.vanniktech.maven.publish") version "0.37.0"
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

tasks.test {
    useJUnitPlatform()
}

// Keep the copyright notice and MIT terms with every distributed JAR.
tasks.withType<Jar>().configureEach {
    from(layout.projectDirectory.file("LICENSE")) {
        into("META-INF")
    }
}

val apiDocumentationJar = tasks.register<Jar>("apiDocumentationJar") {
    archiveClassifier.set("javadoc")
    dependsOn("dokkaGeneratePublicationHtml")
    from(layout.buildDirectory.dir("dokka/html"))
}

mavenPublishing {
    configureBasedOnAppliedPlugins(
        javadocJar = com.vanniktech.maven.publish.JavadocJar.None()
    )
    coordinates(group.toString(), "duzek", version.toString())
    publishToMavenCentral(automaticRelease = false)
    // Publishers enable signing with signAllPublications=true in user Gradle properties.

    pom {
        name.set("Duzek")
        description.set(project.description)
        inceptionYear.set("2026")
        url.set("https://github.com/necdetzr/Duzek")

        licenses {
            license {
                name.set("MIT License")
                url.set("https://opensource.org/license/mit/")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                id.set("necdetzr")
                name.set("Necdet Zorluer")
                url.set("https://github.com/necdetzr")
            }
        }
        scm {
            url.set("https://github.com/necdetzr/Duzek")
            connection.set("scm:git:https://github.com/necdetzr/Duzek.git")
            developerConnection.set("scm:git:ssh://git@github.com/necdetzr/Duzek.git")
        }
    }
}

publishing {
    publications.named<MavenPublication>("maven") {
        artifact(apiDocumentationJar)
    }
}

// Use the local GPG keyring and agent; do not export private keys into the project.
signing {
    useGpgCmd()
}
