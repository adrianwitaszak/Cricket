plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    id(Plugins.CRASHLYTICS)
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
    implementation(Android.timber)
    addAndroidTestDependencies()
    addTestDependencies()
}