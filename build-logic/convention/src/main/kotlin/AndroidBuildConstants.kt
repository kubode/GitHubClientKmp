import org.gradle.api.Project

internal const val COMPILE_SDK = 33
internal const val TARGET_SDK = 33
internal const val MIN_SDK = 26
internal const val JVM_TOOLCHAIN = 17

// e.g. :shared:feature:search -> com.github.kubode.githubclient.shared.feature.search
val Project.namespace: String
    get() = "$group${path.replace(":", ".").replace("-", "")}"
