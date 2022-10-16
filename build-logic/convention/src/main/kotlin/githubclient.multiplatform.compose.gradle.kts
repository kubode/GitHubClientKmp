plugins {
    id("githubclient.multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(compose.preview)
            }
        }
        val androidDebug by getting {
            dependencies {
                implementation(compose.uiTooling)
            }
        }
    }
}
