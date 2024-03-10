import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    jvmToolchain(JVM_TOOLCHAIN)

    jvm()
    androidTarget()
    val configureIos: KotlinNativeTarget.() -> Unit = {
        binaries {
            framework {
                binaryOption("bundleId", project.namespace)
            }
        }
    }
    iosArm64(configureIos)
    iosSimulatorArm64(configureIos)

    sourceSets {
        commonMain {
        }
        commonTest {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        iosMain {
        }
        iosTest {
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(JVM_TOOLCHAIN))
    }
}

android {
    namespace = project.namespace

    compileSdk = COMPILE_SDK
    defaultConfig {
        minSdk = MIN_SDK
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
