plugins {
    id("githubclient.multiplatform.feature")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.shared.core.designsystem)
            }
        }
    }
}
