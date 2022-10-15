plugins {
    kotlin("multiplatform")
    id("githubclient.multiplatform.android")
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
