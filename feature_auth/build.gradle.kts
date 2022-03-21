plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    id(Plugins.GOOGLE_SERVICES)
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

dependencies {
    implementation(project(Modules.CORE))
    implementation(project(Modules.MODEL))
    implementation(project(Modules.DATASOURCE))

    implementation(Google.auth)
    with(Firebase) {
        implementation(platform(bom))
        implementation(auth)
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
        implementation(timber)
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