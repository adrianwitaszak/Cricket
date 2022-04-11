import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = AndroidConfig.javaVersion
        freeCompilerArgs = listOf(
            "-Xskip-prerelease-check",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-opt-in=androidx.compose.animation.ExperimentalAnimationApi",
        )
    }
}
dependencies {
    with(Modules) {
        implementation(project(COMPONENTS))
        implementation(project(USECASES))
    }
    implementation(Koin.compose)
    with(Android) {
        implementation(lifecycle)
        implementation(composeUi)
        implementation(composePreview)
        implementation(composeTooling)
        implementation(composeMaterial)
        implementation(composeFoundation)
//        implementation(composeNavigation)
        implementation(composeConstrainLayout)
        debugImplementation(composeToolingDebug)
        implementation(accompanistNavigationAnimation)
    }
}