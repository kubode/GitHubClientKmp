plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    // jvm("desktop")
    android()
    ios()
    iosSimulatorArm64()

    sourceSets {
        val iosMain by getting {
        }
        val iosTest by getting {
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Test by getting {
            dependsOn(iosTest)
        }
    }
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
