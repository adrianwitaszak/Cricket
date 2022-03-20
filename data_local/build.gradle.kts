plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
}

android {
    namespace = "${AndroidConfig.applicationId}.data.local"
    compileSdk = AndroidConfig.compileSdk
    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        testInstrumentationRunner = AndroidConfig.hiltTestRunner
    }
    compileOptions {
        sourceCompatibility = AndroidConfig.javaVersionName
        targetCompatibility = AndroidConfig.javaVersionName
    }
}

dependencies {
    with(Kotlin) {
        implementation(coroutinesCore)
    }
    with(Hilt) {
        implementation(core)
        kapt(compiler)
    }
    with(Room) {
        implementation(runtime)
        annotationProcessor(compiler)
        kapt(compiler)
    }
}