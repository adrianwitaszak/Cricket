plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
}

android {
    namespace = "${AndroidConfig.applicationId}.data.remote"
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
    }
    addFirebaseAuthDependency()
    addCrashlyticsAnalyticsDependencies()
    with(Firebase) {
        implementation(cloudFirestore)
    }
    with(Kotlin) {
        implementation(coroutinesCore)
        implementation(coroutinesPlayServices)
    }
    implementation(Koin.android)
    addAndroidTestDependencies()
    addTestDependencies()
}