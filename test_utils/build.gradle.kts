plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.HILT)
}
android {
    namespace = "${AndroidConfig.applicationId}.test_utils"
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
    with(Hilt) {
        implementation(core)
        kapt(compiler)
    }
    with(Kotlin) {
        implementation(coroutinesCore)
    }
    with(TestDependencies) {
        implementation(junit4Ktx)
        implementation(junit4)
        implementation(testCore)
        implementation(truth)
        implementation(mockito)
        implementation(coroutinesTest)
    }
}