// build.gradle.kts (Project-level)

plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.20" apply false
}

repositories {
    // Repositorio oficial de Google (para AndroidX, Material, AppCompat, etc.)
    google()
    // Repositorio Maven Central (para otras bibliotecas como Room, Lifecycle, etc.)
    mavenCentral()
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PatioBalmax"
include(":app")
