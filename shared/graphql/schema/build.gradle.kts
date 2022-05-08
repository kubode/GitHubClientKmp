import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    alias(libs.plugins.apollo)
}

configureKotlinMultiplatformProjectWithDefault()

kotlin {
    sourceSets["commonMain"].dependencies {
        api(libs.apollo.api)
    }
    sourceSets["commonTest"].dependencies {
        implementation(libs.kotlin.test)
    }
}

apollo {
    packageName.set("com.github.kubode.graphqlkmm.shared.schema")
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
