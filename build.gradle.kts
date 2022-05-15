buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.kotlin.gradle.plugin)
        classpath(libs.android.gradle.plugin)
    }
}

allprojects {
    group = "com.github.kubode.githubclient"
    version = "1.0"
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
