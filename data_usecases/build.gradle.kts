plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
}

android {
    namespace = "${AndroidConfig.applicationId}.data.usecases"
    compileSdk = AndroidConfig.compileSdk
    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
    }
    compileOptions {
        sourceCompatibility = AndroidConfig.javaVersionName
        targetCompatibility = AndroidConfig.javaVersionName
    }
}

dependencies {
    with(Modules) {
        implementation(project(DOMAIN))
        implementation(project(CORE))
        implementation(project(REPOSITORY))
    }
    with(Kotlin) {
        implementation(coroutinesCore)
        implementation(coroutinesPlayServices)
    }
    implementation(Koin.android)
}