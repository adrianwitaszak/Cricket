object Gradle {
    private const val gradleBuildTool = "7.1.2"
    const val gradle = "com.android.tools.build:gradle:$gradleBuildTool"
}

object Kotlin {
    const val kotlinVersion = "1.6.10"
    private const val coroutinesVersion = "1.6.0"

    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val serialization =
        "org.jetbrains.kotlin:kotlin-serialization:$kotlinVersion"
    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion"
    const val coroutinesPlayServices =
        "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$coroutinesVersion"
}

object Koin {
    private const val version = "3.2.0-beta-1"
    const val android = "io.insert-koin:koin-android:$version"
    const val workManager = "io.insert-koin:koin-androidx-workmanager:$version"
    const val compose = "io.insert-koin:koin-androidx-compose:$version"
    const val test = "io.insert-koin:koin-test:$version"
    const val junit = "io.insert-koin:koin-test-junit4:$version"

}

//object Hilt {
//    private const val version = "2.38.1"
//    private const val navigationComposeVersion = "1.0.0"
//
//    const val gradle = "com.google.dagger:hilt-android-gradle-plugin:$version"
//    const val core = "com.google.dagger:hilt-android:$version"
//    const val compiler = "com.google.dagger:hilt-android-compiler:$version"
//    const val navigationCompose = "androidx.hilt:hilt-navigation-compose:$navigationComposeVersion"
//}

object Room {
    private const val version = "2.4.2"
    const val ktx = "androidx.room:room-ktx:$version"
    const val runtime = "androidx.room:room-runtime:$version"
    const val compiler = "androidx.room:room-compiler:$version"
}

object Google {
    private const val version = "4.3.10"
    private const val authVersion = "20.1.0"
    const val services = "com.google.gms:google-services:$version"
    const val auth = "com.google.android.gms:play-services-auth:$authVersion"
}

object Firebase {
    private const val version = "29.2.1"
    const val bom = "com.google.firebase:firebase-bom:$version"
    const val auth = "com.google.firebase:firebase-auth-ktx"
    const val analytics = "com.google.firebase:firebase-auth-ktx"
    const val crashlytics = "com.google.firebase:firebase-crashlytics"
    const val cloudMessaging = "com.google.firebase:firebase-messaging"
    const val dynamicFeature = "com.google.firebase:firebase-dynamic-module-support"
}

object Android {
    const val appcompat = "androidx.appcompat:appcompat:${Versions.Android.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.Android.core}"
    const val material = "com.google.android.material:material:${Versions.Android.material}"

    // Lifecycle
    const val lifecycle =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.lifecycle}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Android.lifecycle}"
    const val lifecycleSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.Android.lifecycle}"

    // Compose
    const val composeCompiler = "androidx.compose.compiler:compiler:${Versions.Android.composeUi}"
    const val composeRuntime = "androidx.compose.runtime:runtime:${Versions.Android.composeRuntime}"
    const val composeConstrainLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.Android.composeConstrainLayout}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.Android.composeUi}"
    const val composeUiGraphics = "androidx.compose.ui:ui-graphics:${Versions.Android.composeUi}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.Android.composeUi}"
    const val composeMaterialIcons =
        "androidx.compose.material:material-icons-extended:${Versions.Android.composeUi}"
    const val composeUiUtil = "androidx.compose.ui:ui-util:${Versions.Android.composeUi}"
    const val composePreview =
        "androidx.compose.ui:ui-tooling-preview:${Versions.Android.composeUi}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.Android.composeTooling}"
    const val composeFoundation =
        "androidx.compose.foundation:foundation:${Versions.Android.composeUi}"
    const val composeFoundationLayout =
        "androidx.compose.foundation:foundation-layout:${Versions.Android.composeUi}"
    const val composeAnimation =
        "androidx.compose.animation:animation:${Versions.Android.composeUi}"
    const val composeAnimationCore =
        "androidx.compose.animation:animation-core:${Versions.Android.composeUi}"
    const val composeMaterial3 =
        "androidx.compose.material3:material3:${Versions.Android.material3}"
    const val composeNavigation =
        "androidx.navigation:navigation-compose:${Versions.Android.navigation}"
    const val composeActivity =
        "androidx.activity:activity-compose:${Versions.Android.activityCompose}"
    const val composePaging = "androidx.paging:paging-compose:${Versions.Android.pagingCompose}"

    // Helpers
    const val coil = "io.coil-kt:coil-compose:${Versions.Android.coil}"
    const val paging = "androidx.paging:paging-common-ktx:${Versions.Android.paging}"
    const val accompanistInsets =
        "com.google.accompanist:accompanist-insets:${Versions.Android.accompanist}"
    const val accompanistPager =
        "com.google.accompanist:accompanist-pager:${Versions.Android.accompanist}"
    const val accompanistPagerIndicators =
        "com.google.accompanist:accompanist-pager-indicators:${Versions.Android.accompanist}"
    const val accompanistPlaceholder =
        "com.google.accompanist:accompanist-placeholder-material:${Versions.Android.accompanist}"
    const val accompanistSwipeRefresh =
        "com.google.accompanist:accompanist-swiperefresh:${Versions.Android.accompanist}"
    const val accompanistNavigationAnimation =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.Android.accompanist}"
    const val accompanistPermissions =
        "com.google.accompanist:accompanist-permissions:${Versions.Android.accompanist}"
    const val accompanistSystemUiController =
        "com.google.accompanist:accompanist-systemuicontroller:${Versions.Android.accompanist}"
    const val timber = "com.jakewharton.timber:timber:${Versions.Android.timber}"
}

object AndroidTestDependencies {
    // Core library
    const val junit4Ktx = "androidx.test.ext:junit:${Versions.Android.junit4Ktx}"
    const val junit4 = "junit:junit:${Versions.Android.junit4}"
    const val test_core = "androidx.test:core:${Versions.Android.test_core}"
    const val arch_core = "androidx.arch.core:core-testing:${Versions.Android.arch_core}"

    // Espresso dependencies
    const val espresso_core =
        "androidx.test.espresso:espresso-core:${Versions.Android.espresso_core}"

    // Coroutines
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Kotlin.coroutinesCore}"
    const val turbine = "app.cash.turbine:turbine:${Versions.Android.turbine}"

    // Assertions
    const val kotest = "io.kotest:kotest-assertions-core:${Versions.Android.kotest}"

    // JUnit5
    const val jupiterApi = "org.junit.jupiter:junit-jupiter-api:${Versions.Android.junit5}"
    const val jupiterEngineRuntimeOnly =
        "org.junit.jupiter:junit-jupiter-engine:${Versions.Android.junit5}"
    const val jupiterParams =
        "org.junit.jupiter:junit-jupiter-params:${Versions.Android.junit5}"
    const val jupiterVintageRuntimeOnly =
        "org.junit.vintage:junit-vintage-engine:${Versions.Android.junit5}"

    const val test_runner = "androidx.test:runner:${Versions.Android.test_core}"
    const val rules = "androidx.test:rules:${Versions.Android.rules}"

    const val kotlin_junit =
        "org.jetbrains.kotlin:kotlin-test-junit:${Kotlin.kotlinVersion}"
    const val truth = "com.google.truth:truth:${Versions.Android.GoogleTruth}"
    const val GoogleTruth = "androidx.test.ext:truth:${Versions.Android.XTruth}"

    const val mockk = "io.mockk:mockk:${Versions.Android.mockk}"
    const val mockito = "org.mockito.kotlin:mockito-kotlin:${Versions.Android.mockito}"

    // Work
    const val work = "androidx.work:work-testing:${Versions.Android.workManager}"
}
