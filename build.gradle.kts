import java.util.*

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.fml)
    alias(libs.plugins.quick)
    id("maven-publish")
}

val mod_name: String by project
val mod_version: String by project
val mod_id: String by project
val mod_description: String by project
val access_widener: String by project

version = property("mod_version").toString()

base {
    archivesName = mod_name.lowercase()
}

repositories {
    maven("https://jitpack.io")
    maven("https://gitlab.com/api/v4/projects/74192719/packages/maven")
}

loom {
    accessWidenerPath = file("src/main/resources/${property("access_widener")}")
    mergedMinecraftJar()
    fml = File("loader/loader-3.4.2.jar")

    mods {
        create(mod_name) {
            sourceSet(sourceSets.main.get())
        }
    }
}

dependencies {
    minecraft("com.mojang:minecraft:1.6.4-MITE")
    mappings(loom.fmlMCPMappings())
    implementation(files(loom.fml.toPath()))

    implementation(libs.kotlinLanguage)
    implementation(libs.kawakazeLib)
}

val properties = mapOf(
    "id" to mod_id,
    "name" to mod_name,
    "version" to mod_version,
    "description" to mod_description,
    "widener" to access_widener
)

tasks.processResources {
    inputs.property("version", mod_version)

    filesMatching("fml.mod.json") {
        expand(properties)
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

