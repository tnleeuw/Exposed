plugins {
    kotlin("jvm") version "1.4.32" apply true
    id("io.github.gradle-nexus.publish-plugin") apply true
    id("org.jmailen.kotlinter") version "3.4.3"
}

allprojects {
    apply(plugin = "org.jmailen.kotlinter")

    if (this.name != "exposed-tests" && this != rootProject) {
        apply(from = rootProject.file("buildScripts/gradle/publishing.gradle.kts"))
    }
}

nexusPublishing {
    repositories {
        sonatype {
            username.set(System.getenv("exposed.sonatype.user"))
            password.set(System.getenv("exposed.sonatype.password"))
            useStaging.set(true)
        }
    }
}

repositories {
    mavenCentral()
}
