plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

configureKotlinMultiplatformProjectWithDefault()

kotlin {
    sourceSets["commonMain"].dependencies {

    }
    sourceSets["commonTest"].dependencies {
        implementation(libs.kotlin.test)
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
}
