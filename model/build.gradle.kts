plugins {
    java
    kotlin(Plugins.JVM)
}

java {
    sourceCompatibility = AndroidConfig.javaVersionName
    targetCompatibility = AndroidConfig.javaVersionName
}