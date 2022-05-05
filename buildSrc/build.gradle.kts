plugins {
    kotlin("jvm") version libs.versions.kotlin.get()
    `kotlin-dsl`
}

dependencies {
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.android.gradle.plugin)
}
