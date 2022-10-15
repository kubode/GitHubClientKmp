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
    // e.g. :shared:feature:search -> com.github.kubode.githubclient.shared.feature.search
    namespace = "${project.group}${project.path.replace(":", ".").replace("-", "")}"

    compileSdk = COMPILE_SDK
    defaultConfig {
        minSdk = MIN_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    // Move AndroidManifest.xml from src/main to src/androidMain
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
}
