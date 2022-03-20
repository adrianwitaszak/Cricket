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
    ":model",
    ":core",

    ":data_local",
//    ":data_remote",
//    ":data_repository",

    ":feature_auth",
    ":feature_onboarding",
    ":feature_home",
)
