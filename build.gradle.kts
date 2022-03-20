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
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}
