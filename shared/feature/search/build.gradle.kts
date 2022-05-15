plugins {
    id("githubclient.multiplatform")
    alias(libs.plugins.apollo)
}

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
    packageName.set("com.github.kubode.githubclient.shared.feature.search")
}
