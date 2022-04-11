plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    id(Plugins.CRASHLYTICS)
}

android {
    namespace = "${AndroidConfig.applicationId}.core"
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
    }
    with(Firebase) {
        implementation(cloudFirestore)
    }
    addCrashlyticsAnalyticsDependencies()
    implementation(Koin.android)
    implementation(Android.timber)
}