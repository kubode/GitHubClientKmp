import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    alias(libs.plugins.apollo)
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        framework {
            baseName = "schema"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.apollo.runtime)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 26
        targetSdk = 32
    }
}

apollo {
    packageName.set("com.github.kubode.graphqlkmm.shared.schema")
    schemaFile.set(file("src/commonMain/graphql/com/github/kubode/graphqlkmm/shared/schema/schema.graphqls"))
}

// ./gradlew downloadApolloSchema
tasks.withType<com.apollographql.apollo3.gradle.internal.ApolloDownloadSchemaTask>().all {
    endpoint.set("https://api.github.com/graphql")
    schema.set("$projectDir/src/commonMain/graphql/com/github/kubode/graphqlkmm/shared/schema/schema.graphqls")
    val token = loadProperties("$rootDir/local.properties").getProperty("GITHUB_TOKEN")
    if (token.isNullOrEmpty()) throw IllegalStateException("`GITHUB_TOKEN` not exists in `local.properties`")
    header = header + "Authorization: bearer $token"
}
