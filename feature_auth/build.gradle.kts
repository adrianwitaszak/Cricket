plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
}
android {
    namespace = "${AndroidConfig.applicationId}.feature.auth"
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
        implementation(project(CORE))
        implementation(project(MODEL))
        implementation(project(DATASOURCE))
    }
    with(Kotlin) {
        implementation(coroutinesCore)
        implementation(coroutinesAndroid)
    }
    with(Hilt) {
        implementation(core)
        kapt(compiler)
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
}