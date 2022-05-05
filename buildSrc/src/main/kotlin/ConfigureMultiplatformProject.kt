import com.android.build.gradle.LibraryExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

fun Project.configureKotlinMultiplatformProjectWithDefault() {

    configure<KotlinMultiplatformExtension> {
        android()
        ios()
        // Apple Silicon target
        iosSimulatorArm64()
        sourceSets["iosSimulatorArm64Main"].dependsOn(sourceSets["iosMain"])
        sourceSets["iosSimulatorArm64Test"].dependsOn(sourceSets["iosTest"])

        // opt-in @OptIn
        sourceSets.all {
            languageSettings {
                optIn("kotlin.RequiresOptIn")
            }
        }

        // Exports KDoc to iOS
        targets.withType<KotlinNativeTarget> {
            compilations["main"].kotlinOptions.freeCompilerArgs += "-Xexport-kdoc"
        }
    }

    configure<LibraryExtension> {
        compileSdk = 32
        sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
        defaultConfig {
            minSdk = 26
            targetSdk = 32
        }
    }
}
