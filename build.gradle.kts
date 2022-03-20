buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Gradle.gradle)
        classpath(Kotlin.kotlinGradle)
        classpath(Hilt.gradle)
        classpath(Google.services)
    }
}
