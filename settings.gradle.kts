@file:Suppress("UnstableApiUsage")
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Cricket"

include(

    ":app",
    ":domain",
    ":core",
    ":components",

    ":data_remote",
    ":data_repository",
    ":data_usecases",

    ":feature_auth",
    ":feature_onboarding",
    ":feature_home",

    ":test_utils",
)