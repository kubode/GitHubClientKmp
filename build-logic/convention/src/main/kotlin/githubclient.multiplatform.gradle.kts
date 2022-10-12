plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    // jvm("desktop")
    android()
    ios()
    iosSimulatorArm64()
    // TODO: Move to sourceSets {} and dependsOn(iosMain)
    sourceSets["iosSimulatorArm64Main"].dependsOn(sourceSets["iosMain"])
    sourceSets["iosSimulatorArm64Test"].dependsOn(sourceSets["iosTest"])
}

android {
    compileSdk = COMPILE_SDK
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = MIN_SDK
        targetSdk = TARGET_SDK
    }
}
