plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
}

android {
    namespace = "${AndroidConfig.applicationId}.feature.home"
    compileSdk = AndroidConfig.compileSdk
    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Android.composeUi
    }
    compileOptions {
        sourceCompatibility = AndroidConfig.javaVersionName
        targetCompatibility = AndroidConfig.javaVersionName
    }
}

dependencies {
    with(Firebase) {
        implementation(platform(bom))
        implementation(analytics)
    }
    with(Kotlin) {
        implementation(coroutinesCore)
        implementation(coroutinesAndroid)
        implementation(coroutinesPlayServices)
    }
    with(Koin) {
        implementation(compose)
    }
    with(Android) {
        implementation(lifecycle)
        implementation(composeActivity)
        implementation(composeUi)
        implementation(composeTooling)
        implementation(composeFoundation)
        implementation(composeMaterial)
        implementation(composeMaterial3)
        implementation(composeConstrainLayout)
    }
}