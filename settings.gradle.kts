/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

val pluginName: String by settings
rootProject.name = pluginName

pluginManagement {
    val kotlinVersion: String by settings
    val shadowJarVersion: String by settings
    val pluginYmlVersion: String by settings
    plugins {
        kotlin("jvm") version kotlinVersion
        id("com.github.johnrengelman.shadow") version shadowJarVersion
        id("net.minecrell.plugin-yml.bukkit") version pluginYmlVersion
    }
}