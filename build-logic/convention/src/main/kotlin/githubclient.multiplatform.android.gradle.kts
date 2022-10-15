plugins {
    id("com.android.library")
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
