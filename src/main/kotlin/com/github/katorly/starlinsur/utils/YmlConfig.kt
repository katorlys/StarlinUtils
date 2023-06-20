/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.utils

import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.InputStreamReader

/**
 * Spigot-CustomConfig (https://github.com/katorlys/Spigot-CustomConfig)
 * Copyright (c) 2020-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

class YmlConfig(private val plugin: JavaPlugin, private val name: String = "config.yml", private val path: String = "") {
    private var file: File
    private var filepath: File = File(plugin.dataFolder, path)
    private var config: FileConfiguration

    /**
     * Register and create the config file.
     *
     * @author Katorly
     */
    init {
        file = File(filepath, name)
        config = YamlConfiguration.loadConfiguration(file)
    }

    /**
     * Create the default config if the config does not exist.
     *
     */
    fun saveDefaultConfig() {
        if (!filepath.exists()) {
            val success = filepath.mkdirs()
            if (!success) Bukkit.getLogger()
                .severe("[" + plugin.description.name + "] Error creating the config. Please try again.")
        }
        if (!file.exists()) plugin.saveResource(path + name, false)
    }

    /**
     * Get values in the config.
     *
     * @return
     */
    fun getConfig(): FileConfiguration {
        if (!filepath.exists()) saveDefaultConfig()
        return config
    }

    /**
     * Reload the config.
     *
     */
    fun reloadConfig() {
        saveDefaultConfig()
        config = YamlConfiguration.loadConfiguration(file)
        val stream = plugin.getResource(name)
        if (stream != null) {
            val ymlFile = YamlConfiguration.loadConfiguration(InputStreamReader(stream))
            config.setDefaults(ymlFile)
        }
    }

    /**
     * Save the config to apply changes.
     *
     */
    fun saveConfig() {
        try {
            config.save(file)
        } catch (t: Throwable) {
            t.printStackTrace()
            Bukkit.getLogger().severe("[" + plugin.name + "] Error saving the config. Please try again.")
        }
    }
}

/**
 * Execute the function of saveConfig() and reloadConfig() at the same time.
 *
 */
fun YmlConfig.save() {
    this.saveConfig()
    this.reloadConfig()
}