plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

configureKotlinMultiplatformProjectWithDefault()

kotlin {
    sourceSets["commonMain"].dependencies {
        api(libs.apollo.runtime)
    }
    sourceSets["commonTest"].dependencies {
        implementation(libs.kotlin.test)
    }
}
