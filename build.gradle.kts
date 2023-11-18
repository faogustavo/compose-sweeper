plugins {
    kotlin("multiplatform") version "1.9.20"
    id("org.jetbrains.compose") version "1.5.10"
}

group = "dev.valvassori"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    js(IR) {
        browser {
            testTask {
                testLogging.showStandardStreams = true
                useKarma {
                    useChromeHeadless()
                }
            }
        }
        binaries.executable()
    }
    sourceSets {
        all {
            languageSettings.apply {
                optIn("kotlin.ExperimentalStdlibApi")
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(compose.html.core)
                implementation(compose.runtime)
            }
        }
        val jsTest by getting {
            dependencies {
                implementation(kotlin("test-js"))
            }
        }
    }
}