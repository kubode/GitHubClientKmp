import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    id("githubkmm.multiplatform")
    kotlin("native.cocoapods")
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
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../../iosApp/Podfile")
        name = "shared"
        framework {
            baseName = "shared"
        }
    }
}
