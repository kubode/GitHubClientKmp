plugins {
    kotlin("multiplatform")
    id("com.android.library")
    alias(libs.plugins.apollo)
}

configureKotlinMultiplatformProjectWithDefault()

kotlin {
    sourceSets["commonMain"].dependencies {
        apolloMetadata(projects.shared.schema)
        implementation(projects.shared.schema)
    }
    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test"))
    }
}

apollo {
    packageName.set("com.github.kubode.graphqlkmm.shared.feature.search")
}
