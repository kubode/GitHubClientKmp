plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

configureKotlinMultiplatformProjectWithDefault()

kotlin {
    sourceSets["commonMain"].dependencies {
        api(libs.apollo.runtime)
        api(libs.apollo.normalized.cache)
    }
    sourceSets["commonTest"].dependencies {
        implementation(libs.kotlin.test)
        implementation(libs.bundles.apollo.testing)
    }
}
