import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    // jvm("desktop")
    android()
    ios()
    // TODO: compose-jb:1.2.0-alpha01-dev686 not supports yet.
    // Apple Silicon target
//    iosSimulatorArm64()
//    sourceSets["iosSimulatorArm64Main"].dependsOn(sourceSets["iosMain"])
//    sourceSets["iosSimulatorArm64Test"].dependsOn(sourceSets["iosTest"])

    // opt-in @OptIn
    sourceSets.all {
        languageSettings {
            optIn("kotlin.RequiresOptIn")
        }
    }

    // Exports KDoc to iOS
    targets.withType<KotlinNativeTarget> {
        compilations["main"].kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
    }
}

// Workaround: https://github.com/JetBrains/compose-jb/pull/2164
tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java) {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

android {
    compileSdk = 33
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 26
        targetSdk = 33
    }
}
