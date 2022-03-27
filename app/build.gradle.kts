import com.android.aaptcompiler.compileResource
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id(Plugins.ANDROID_APPLICATION)
    kotlin(Plugins.KOTLIN_ANDROID)
    id(Plugins.GOOGLE_SERVICES)
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
        testInstrumentationRunner = "com.adwi.cricket.androidtest.HiltTestRunner"
//            AndroidConfig.hiltTestRunner
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

dependencies {
    with(Modules) {
        implementation(project(MODEL))
        implementation(project(CORE))
        implementation(project(DATASOURCE))
        implementation(project(AUTH))
        implementation(project(ONBOARDING))
        implementation(project(HOME))
        implementation(project(TEST_UTILS))
    }
    implementation(Koin.compose)
    addFirebaseAuthDependency()
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
    addAndroidTestDependencies()
    addTestDependencies()
}