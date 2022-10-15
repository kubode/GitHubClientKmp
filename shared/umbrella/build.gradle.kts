plugins {
    id("githubclient.multiplatform")
    kotlin("native.cocoapods")
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    sourceSets["commonMain"].dependencies {
        // depends all feature modules
        projects.shared.feature.dependencyProject.subprojects.forEach { implementation(it) }
        implementation(projects.shared.graphql.client)
        implementation(compose.ui)
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../../iosApp/Podfile")
        name = "shared"
    }
}
