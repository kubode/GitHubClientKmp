import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("githubclient.multiplatform")
    kotlin("native.cocoapods")
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    val featureSubprojects = projects.shared.feature.dependencyProject.subprojects

    targets.filterIsInstance<KotlinNativeTarget>().forEach { target ->
        target.binaries.framework {
            // export all feature modules
            featureSubprojects.forEach { export(it) }
        }
    }

    sourceSets["commonMain"].dependencies {
        // depends all feature modules as api
        featureSubprojects.forEach { api(it) }
        dependencies {
            implementation(compose.ui)
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../../iosApp/Podfile")
        name = "shared"
        framework {
            baseName = "shared"
            // export all feature modules
            featureSubprojects.forEach { export(it) }
        }
    }
}
