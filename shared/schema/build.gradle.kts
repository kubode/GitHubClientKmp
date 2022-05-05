import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    alias(libs.plugins.apollo)
}

configureKotlinMultiplatformProjectWithDefault()

kotlin {
    sourceSets["commonMain"].dependencies {
        implementation(libs.apollo.api)
    }
    sourceSets["commonTest"].dependencies {
        implementation(libs.kotlin.test)
    }
}

apollo {
    // Run `./gradlew downloadGithubApolloSchema` updates `schema.graphqls`.
    service("github") {
        packageName.set("com.github.kubode.graphqlkmm.shared.schema")
        introspection {
            endpointUrl.set("https://api.github.com/graphql")
            schemaFile.set(file("src/commonMain/graphql/com/github/kubode/graphqlkmm/shared/schema/schema.graphqls"))
            val token = loadProperties("$rootDir/local.properties").getProperty("GITHUB_TOKEN")
            if (token.isNullOrEmpty()) throw IllegalStateException("`GITHUB_TOKEN` not exists in `local.properties`")
            headers.put("Authorization", "bearer $token")
        }
    }
}
