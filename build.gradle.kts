// build.gradle.kts (Project-level)

plugins {
    id("com.android.application") version "8.10.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
    id("org.jetbrains.kotlin.kapt") version "1.9.20"
}

repositories {
    // Repositorio oficial de Google (donde se encuentran todas las librerías de AndroidX)
    google()
    // Repositorio Maven Central (para otras dependencias como Room, Lifecycle, etc.)
    mavenCentral()
}
