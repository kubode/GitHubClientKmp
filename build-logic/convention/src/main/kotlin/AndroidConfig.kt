import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.AppExtension
import com.android.build.gradle.LibraryExtension
import org.gradle.kotlin.dsl.get

internal const val COMPILE_SDK = 33
internal const val TARGET_SDK = 33
internal const val MIN_SDK = 26

internal fun CommonExtension<*, *, *, *>.configureMultiplatform() {
    namespace = "com.github.kubode.githubclient.android"
    compileSdk = COMPILE_SDK
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = MIN_SDK
    }

    when (this) {
        is AppExtension -> configureAppExtension()
        is LibraryExtension -> configureLibraryExtension()
    }
}

private fun AppExtension.configureAppExtension() {
    defaultConfig {
        targetSdk = TARGET_SDK
    }
}

private fun LibraryExtension.configureLibraryExtension() {
    namespace = "".replace(":", ".").replace("-", "")
}
