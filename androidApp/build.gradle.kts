plugins {
    id("githubclient.android.application")
    alias(libs.plugins.compose.multiplatform)
}

dependencies {
    implementation(projects.shared.umbrella)
    implementation(libs.androidx.activity.compose)
}
