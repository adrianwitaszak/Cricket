plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    id(Plugins.GOOGLE_SERVICES)
}

android {
    namespace = "${AndroidConfig.applicationId}.datasource"
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
        implementation(project(MODEL))
    }
    with(Firebase) {
        implementation(cloudFirestore)
        implementation(cloudMessaging)
    }
    with(Kotlin) {
        implementation(coroutinesPlayServices)
    }
    implementation(Koin.android)
    addFirebaseAuthDependency()
    addAndroidTestDependencies()
    addTestDependencies()
}