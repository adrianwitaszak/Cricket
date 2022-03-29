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
        classpath(Firebase.crashlyticsGradle)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    }
}