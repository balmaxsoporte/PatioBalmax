plugins {
    id("com.android.application") version "8.1.0" apply false
    id("org.jetbrains.kotlin.android") version "1.8.20" apply false
}

subprojects {
    apply(plugin = "com.android.application")
    apply(plugin = "org.jetbrains.kotlin.android")

    dependencies {
        implementation("androidx.core:core-ktx:1.10.0")
        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("com.google.android.material:material:1.9.0")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.1.5")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    }

    android {
    namespace = "com.example.patiobalmax"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.patiobalmax"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    }
}
