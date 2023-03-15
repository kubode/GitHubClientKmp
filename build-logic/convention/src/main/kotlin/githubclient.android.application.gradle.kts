plugins {
    id("com.android.application")
    kotlin("android")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(JVM_TOOLCHAIN))
    }
}

android {
    namespace = "${project.group}.android"

    compileSdk = COMPILE_SDK
    defaultConfig {
        minSdk = MIN_SDK
        targetSdk = TARGET_SDK
        // TODO: set version
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
