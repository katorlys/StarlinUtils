import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-library`
    kotlin("jvm") version "1.8.0"
    id("io.izzel.taboolib") version "1.56"
}

taboolib {
    description {
        desc("星林宇宙 (StarlinWorld) 服务器定制插件")
        contributors {
            name("Katorly")
        }
    }
    install("common", "common-5", "module-chat", "module-configuration", "module-database", "module-ui")
    install("platform-bukkit")
    install("expansion-command-helper")
    install("expansion-player-database")
    classifier = null
    version = "6.0.10-98"
}

val spigotVersion : String by project
val pluginGroup : String by project
val pluginVersion : String by project

group = project.property("pluginGroup") as String
version = project.property("pluginVersion") as String

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
//    maven("https://repo.tabooproject.org/repository/releases")
//    maven("https://repo.onarandombox.com/content/groups/public/")
}

dependencies {
    implementation(kotlin("stdlib"))
    compileOnly("org.spigotmc:spigot-api:$spigotVersion")
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("ink.ptms.core:v11902:11902-minimize:mapped")
    compileOnly("ink.ptms.core:v11902:11902-minimize:universal")
    compileOnly(fileTree("libs"))
//    compileOnly("com.onarandombox.multiversecore:Multiverse-Core:4.3.2-SNAPSHOT") // Fck Multiverse-Core - latest version x complete in mvn repo
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
}