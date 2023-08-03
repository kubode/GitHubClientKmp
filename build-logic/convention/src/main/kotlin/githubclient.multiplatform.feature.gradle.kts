plugins {
    id("githubclient.multiplatform.compose")
    id("com.apollographql.apollo3")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:graphql:client"))
                implementation(project(":shared:graphql:schema"))
            }
        }
    }
}

dependencies {
    apolloMetadata(project(":shared:graphql:schema"))
}

apollo {
    service("github") {
        packageName.set(project.namespace)
    }
}
