import org.gradle.api.Project

internal const val COMPILE_SDK = 34
internal const val TARGET_SDK = 34
internal const val MIN_SDK = 26
internal const val JVM_TOOLCHAIN = 19

// e.g. :shared:feature:search -> com.github.kubode.githubclient.shared.feature.search
val Project.namespace: String
    get() = "$group${path.replace(":", ".").replace("-", "")}"
