import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestLogEvent
import java.net.URL

plugins {
    kotlin("jvm") version "1.4.30"
    kotlin("plugin.serialization") version "1.4.31"
    id("io.gitlab.arturbosch.detekt") version "1.15.0"
    id("org.jetbrains.dokka") version "1.4.20"
    application
}

group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("junit:junit:4.12")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.1")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.14.2")
    implementation("com.charleskorn.kaml:kaml:0.28.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.1.0")
    implementation("com.squareup:kotlinpoet:1.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    implementation("org.jfree:jfreechart:1.5.3")
    implementation("org.jfree:jcommon:1.0.24")
}

detekt {
    failFast = true // fail build on any finding
    config = files("config/detekt/detekt.yml")
    buildUponDefaultConfig = true
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(
            TestLogEvent.STANDARD_ERROR, TestLogEvent.STARTED, TestLogEvent.PASSED, TestLogEvent.FAILED, TestLogEvent.SKIPPED
        )
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Werror")
    }
}

tasks.dokkaHtml {
    dokkaSourceSets {
        outputDirectory.set(buildDir.resolve("dokka"))
        moduleName.set("kotlin_homeworks")
        configureEach {
            platform.set(org.jetbrains.dokka.Platform.jvm)

            sourceLink {
                localDirectory.set(file("src/main/kotlin"))
                remoteUrl.set(URL("https://github.com/faf0be172/Kotlin_homeworks/tree/master/src/main/kotlin"))
                remoteLineSuffix.set("#L")
            }
        }
    }
}

application {
    mainClass.set("MainKt")
}
