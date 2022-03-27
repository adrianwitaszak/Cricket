plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
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
kapt {
    correctErrorTypes = true
}

dependencies {
    with(Modules) {
        implementation(project(MODEL))
    }
    api(Google.auth)
    with(Firebase) {
        implementation(platform(bom))
        api(auth)
        implementation(analytics)
        implementation(cloudFirestore)
        implementation(cloudMessaging)
    }
    with(Kotlin) {
        implementation(coroutinesPlayServices)
    }
    with(Hilt) {
        implementation(core)
        kapt(compiler)
    }
}