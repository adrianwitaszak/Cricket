plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
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
    }
    with(Hilt) {
        implementation(core)
        kapt(compiler)
    }
    with(Room) {
        api(ktx)
        api(runtime)
        kapt(compiler)
    }
}