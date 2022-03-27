plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
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
kapt {
    correctErrorTypes = true
}
dependencies {
    with(Modules) {
        implementation(project(DATASOURCE))
    }
    with(Firebase) {
        implementation(platform(bom))
        implementation(analytics)
    }
    with(Kotlin) {
        implementation(coroutinesCore)
        implementation(coroutinesAndroid)
        implementation(coroutinesPlayServices)
    }
    with(Hilt) {
        implementation(core)
        kapt(compiler)
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