import com.android.aaptcompiler.compileResource
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.KOTLIN_ANDROID)
    kotlin(Plugins.KOTLIN_KAPT)
    id(Plugins.GOOGLE_SERVICES)
    id(Plugins.HILT)
}
android {
    namespace = AndroidConfig.applicationId
    compileSdk = AndroidConfig.compileSdk
    defaultConfig {
        applicationId = AndroidConfig.applicationId
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName
        testInstrumentationRunner = AndroidConfig.testInstrumentationRunner
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.Android.composeUi
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro")
        }
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
kapt {
    correctErrorTypes = true
}

dependencies {
    with(Modules) {
        implementation(project(MODEL))
        implementation(project(DATASOURCE))
        implementation(project(AUTH))
        implementation(project(ONBOARDING))
        implementation(project(HOME))
    }
    with(Hilt) {
        implementation(core)
        implementation(navigationCompose)
        kapt(compiler)
    }
    with(Android) {
        implementation(timber)
        implementation(coreKtx)
        implementation(appcompat)
        implementation(lifecycle)
        implementation(composeUi)
        implementation(composeTooling)
        implementation(composeActivity)
        implementation(composeMaterial)
        implementation(lifecycle)
        implementation(composeNavigation)
        implementation(accompanistInsets)
        debugImplementation(composeToolingDebug)
        implementation(accompanistNavigationAnimation)
        implementation(accompanistSystemUiController)
    }
}