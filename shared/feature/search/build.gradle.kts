plugins {
    kotlin("multiplatform")
    id("com.android.library")
    alias(libs.plugins.apollo)
}

configureKotlinMultiplatformProjectWithDefault()

kotlin {
    sourceSets["commonMain"].dependencies {
        implementation(projects.shared.graphql.schema)
    }
    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test"))
    }
}

dependencies {
    apolloMetadata(projects.shared.graphql.schema)
}

apollo {
    packageName.set("com.github.kubode.graphqlkmm.shared.feature.search")
}
