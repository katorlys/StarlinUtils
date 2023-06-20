/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val spigotVersion: String by project
val pluginName: String by rootProject
val pluginAuthor: String by rootProject
val pluginGroup: String by rootProject
val pluginVersion: String by rootProject
val pluginMain: String by rootProject
val pluginAPI: String by rootProject
val pluginDesc: String by rootProject

val exposedVersion = "0.41.1"

plugins {
    java
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.8.20"
    id("com.github.johnrengelman.shadow") // Documentation: https://github.com/johnrengelman/shadow
    id("net.minecrell.plugin-yml.bukkit") // Documentation: https://github.com/Minecrell/plugin-yml
}

group = project.property("pluginGroup") as String
version = project.property("pluginVersion") as String

repositories {
    maven("https://maven.aliyun.com/repository/public/") // Mirror: Comment this if you are not in China
    maven("https://repo.huaweicloud.com/repository/maven/") // Mirror: Comment this if you are not in China
    mavenLocal()
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation(kotlin("stdlib"))

    // Server Core
    compileOnly("org.spigotmc:spigot-api:$spigotVersion")

    // Dependencies to shadow into the plugin
    // * implementation() is for dependencies to shadow,
    // * compileOnly() is for dependencies not to shadow.
    // implementation("yours")

    // Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Database
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")

    // SQLite
    implementation("org.xerial:sqlite-jdbc:3.30.1")

    // Local dependencies
    compileOnly(fileTree("libs"))
}

val commandsToRegister = arrayOf("starlin", "spawn", "sethome", "home", "homes", "delhome", "back")

bukkit {
    name = pluginName
    version = pluginVersion
    apiVersion = pluginAPI
    main = pluginMain
    author = pluginAuthor
    description = pluginDesc
    commands {
        commandsToRegister.forEach { register(it) }
    }
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }
    }
    withType<ShadowJar> {
        archiveBaseName.set(pluginName)
        archiveClassifier.set("")

        from(sourceSets.main.get().output)

        // Relocate packages
        // relocate("yours", "${rootProject.group}.yours")
    }
}