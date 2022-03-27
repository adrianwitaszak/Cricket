import org.gradle.api.JavaVersion

object AndroidConfig {
    const val compileSdk = 32
    const val applicationId = "com.adwi.cricket"
    const val minSdk = compileSdk
    const val targetSdk = compileSdk
    const val versionCode = 1
    const val versionName = "1.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val hiltTestRunner = "com.adwi.cricket.test.utils.HiltTestRunner"

    const val javaVersion = "1.8"
    val javaVersionName = JavaVersion.VERSION_1_8
}
