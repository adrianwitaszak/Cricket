plugins {
    id(Plugins.ANDROID_LIBRARY)
    kotlin(Plugins.KOTLIN_ANDROID)
}

android {
    namespace = "${AndroidConfig.applicationId}.feature.onboarding"
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
    implementation(project(Modules.COMPONENTS))
    implementation(Koin.compose)
    with(Android) {
        implementation(lifecycle)
        implementation(composeUi)
        implementation(composeTooling)
        implementation(composeMaterial)
        implementation(composeFoundation)
        implementation(composeConstrainLayout)
        debugImplementation(composeToolingDebug)
    }
}