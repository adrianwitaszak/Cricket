plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
}

android {
    namespace = "${AndroidConfig.applicationId}.data.repository"
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
    with(Modules) {
        implementation(project(MODEL))
//        implementation(project(CORE))
        implementation(project(DATA_LOCAL))
        implementation(project(DATA_REMOTE))
    }
    with(Kotlin) {
        implementation(coroutinesCore)
    }
    with(Hilt) {
        implementation(core)
        kapt(compiler)
    }
}