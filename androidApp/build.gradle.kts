plugins {
    id("com.android.application")
    kotlin("android")
    alias(libs.plugins.compose.multiplatform)
}

android {
    compileSdk = 33
    defaultConfig {
        applicationId = "com.github.kubode.githubclient.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(projects.shared.umbrella)
    implementation(libs.androidx.activity.compose)
}
