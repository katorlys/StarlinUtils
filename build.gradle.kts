/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// Modify these in gradle.properties
val pluginName: String by project
//val spigotVersion: String by project
val pluginGroup: String by project
val pluginVersion: String by project

plugins {
    java
    kotlin("jvm") version "1.8.0"
    id("io.izzel.taboolib") version "1.56"
}

taboolib {
    description {
        name(pluginName)
        desc("星林宇宙 (StarlinWorld) 服务器定制插件")
        contributors {
            name("Katorly")
        }
        dependencies {
            name("LuckPerms").optional(true)
        }
    }
    install("common", "common-5")
    install("module-chat", "module-configuration", "module-database", "module-ui")
    install("platform-bukkit")
    install("expansion-player-database")
//    relocate("kotlinx.datetime", "$group.kotlinx.datetime") // 不要用 relocate, 不然依赖打包不进去
    classifier = null
    version = "6.0.10-98"
}

group = project.property("pluginGroup") as String
version = project.property("pluginVersion") as String

repositories {
    maven("https://maven.aliyun.com/repository/public/") // Mirror: Comment this if you are not in China
    maven("https://repo.huaweicloud.com/repository/maven/") // Mirror: Comment this if you are not in China
    mavenLocal()
    mavenCentral()
//    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
//    maven("https://repo.tabooproject.org/repository/releases")
}

dependencies {
    compileOnly(kotlin("stdlib"))
//    compileOnly("org.spigotmc:spigot-api:$spigotVersion")

    compileOnly("ink.ptms.core:v11902:11902-minimize:mapped")
    compileOnly("ink.ptms.core:v11902:11902-minimize:universal")

    taboo("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0") // 要打包进去的依赖必须用 taboo
    compileOnly("net.luckperms:api:5.4")
    compileOnly(fileTree("libs"))
}

tasks {
    withType<JavaCompile> {
        options.encoding = "UTF-8"
        jar {
            destinationDirectory.set(file("$buildDir")) // Delete this if you want to generate the jar file in build/libs
        }
    }
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs = listOf("-Xjvm-default=all")
        }
    }
}