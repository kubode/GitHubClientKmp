import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("githubclient.multiplatform")
    alias(libs.plugins.apollo)
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.apollo.api)
            }
        }
    }
}

apollo {
    packageName.set(project.namespace)
    // Run `./gradlew downloadServiceApolloSchemaFromIntrospection` updates `schema.graphqls`.
    introspection {
        endpointUrl.set("https://api.github.com/graphql")
        schemaFile.set(file("src/commonMain/graphql/schema.graphqls"))
        val token = loadProperties("$rootDir/local.properties").getProperty("GITHUB_TOKEN")
        if (token.isNullOrEmpty()) throw IllegalStateException("`GITHUB_TOKEN` not exists in `local.properties`")
        headers.put("Authorization", "bearer $token")
    }
    // To make this module to schema-module
    // https://www.apollographql.com/docs/kotlin/advanced/multi-modules
    generateApolloMetadata.set(true)
}
