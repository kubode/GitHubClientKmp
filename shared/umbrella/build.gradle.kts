plugins {
    id("githubclient.multiplatform.compose")
    kotlin("native.cocoapods")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                // depends all feature modules
                projects.shared.feature.dependencyProject.subprojects.forEach { implementation(it) }
            }
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../../iosApp/Podfile")
        name = "shared"
    }
}
