import gradle.kotlin.dsl.accessors._6c62bfe83ffd132b6e9b5c9e42b6a39d.sourceSets

plugins {
    id("githubclient.multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared:graphql:client"))
                implementation(project(":shared:graphql:schema"))
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}
