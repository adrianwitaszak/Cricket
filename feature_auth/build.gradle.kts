plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
}
android {
    namespace = "${AndroidConfig.applicationId}.feature.auth"
    compileSdk = AndroidConfig.compileSdk
    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        testInstrumentationRunner = AndroidConfig.hiltTestRunner
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
    with(Modules) {
        implementation(project(CORE))
        implementation(project(MODEL))
        implementation(project(DATASOURCE))
        implementation(project(TEST_UTILS))
    }
    implementation(Firebase.bom)
    implementation(Google.auth)
    implementation(Firebase.auth)
    with(Kotlin) {
        implementation(coroutinesAndroid)
    }
    with(Android) {
        implementation(timber)
        implementation(lifecycle)
        implementation(composeUi)
        implementation(composeTooling)
        implementation(composeActivity)
        implementation(composeMaterial)
        implementation(composeMaterial3)
        implementation(composeFoundation)
        implementation(composeConstrainLayout)
        debugImplementation(composeToolingDebug)
    }
    implementation(Koin.compose)
    addAndroidTestDependencies()
    addTestDependencies()
}