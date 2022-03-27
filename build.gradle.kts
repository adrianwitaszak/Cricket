buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Gradle.gradle)
        classpath(Kotlin.kotlinGradle)
        classpath(Google.services)
    }
}