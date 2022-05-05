plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

configureKotlinMultiplatformProjectWithDefault()

kotlin {
    sourceSets["commonMain"].dependencies {

    }
    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test"))
    }
}
