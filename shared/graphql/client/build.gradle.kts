import org.jetbrains.kotlin.gradle.dsl.KotlinCompile
import org.jetbrains.kotlin.konan.properties.loadProperties

plugins {
    id("githubkmm.multiplatform")
}

kotlin {
    sourceSets["commonMain"].dependencies {
        api(libs.apollo.runtime)
        api(libs.apollo.normalized.cache)
    }
    sourceSets["commonTest"].dependencies {
        implementation(libs.kotlin.test)
        implementation(libs.bundles.apollo.testing)
    }
}

// region: Generate GITHUB_TOKEN
val generatedGithubTokenSourceDir = file("$buildDir/generated/github-token")

kotlin {
    sourceSets {
        getByName("commonMain") {
            kotlin.srcDirs(generatedGithubTokenSourceDir)
        }
    }
}

val generateGithubToken by tasks.registering {
    val packageName = "com.github.kubode.shared.graphql.client"
    val fileName = "GeneratedGitHubToken.kt"
    val propName = "generatedGitHubToken"
    val githubToken = loadProperties("$rootDir/local.properties").getProperty("GITHUB_TOKEN")

    inputs.property(propName, githubToken)
    outputs.dir(generatedGithubTokenSourceDir)

    doLast {
        generatedGithubTokenSourceDir.delete()
        val outputDir = File(generatedGithubTokenSourceDir, packageName.replace('.', File.separatorChar))
        mkdir(outputDir)

        File(outputDir, fileName).writeText(
            """
                package $packageName
                
                internal const val GITHUB_TOKEN = "$githubToken"
            """.trimIndent()
        )
    }
}

tasks.withType<KotlinCompile<*>>().all { dependsOn(generateGithubToken) }
// endregion
