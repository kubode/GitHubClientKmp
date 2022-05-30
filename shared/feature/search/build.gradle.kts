plugins {
    id("githubclient.multiplatform")
    alias(libs.plugins.apollo)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    sourceSets["commonMain"].dependencies {
        implementation(projects.shared.graphql.schema)
        api(compose.ui)
        api(compose.foundation)
        api(compose.material)
        api(compose.runtime)
    }
    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test"))
    }
}

dependencies {
    apolloMetadata(projects.shared.graphql.schema)
}

apollo {
    packageName.set("com.github.kubode.githubclient.shared.feature.search")
}
