import org.gradle.kotlin.dsl.java
import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec

object Plugins {
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"
    const val JVM = "jvm"
    const val KOTLIN_ANDROID = "android"
    const val KOTLIN_KAPT = "kapt"
    const val HILT = "dagger.hilt.android.plugin"
    const val SERIALIZATION = "plugin.serialization"
    const val GOOGLE_SERVICES = "com.google.gms.google-services"
    const val CRASHLYTICS = "com.google.firebase.crashlytics"
}

fun PluginDependenciesSpec.addAndroidPlugins() {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.KOTLIN_ANDROID)
}

fun PluginDependenciesSpec.addFirebasePlugins() {
    id(Plugins.CRASHLYTICS)
}

fun PluginDependenciesSpec.addKotlinLibraryPlugins() {
    java
    kotlin(Plugins.JVM)
}