import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

internal fun VersionCatalog.version(alias: String) = findVersion(alias).get()
internal fun VersionCatalog.library(alias: String) = findLibrary(alias).get()
internal fun VersionCatalog.bundle(alias: String) = findBundle(alias).get()
internal fun VersionCatalog.plugin(alias: String) = findPlugin(alias).get()
