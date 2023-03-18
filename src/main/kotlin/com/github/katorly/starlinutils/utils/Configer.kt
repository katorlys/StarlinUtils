package com.github.katorly.starlinutils.utils

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

class Configer(private val plugin: JavaPlugin, private val path: String, private val name: String) {
    private var file: File?
    private var filepath: File?
    private var config: FileConfiguration? = null

    /**
     * Register and create the config file.
     *
     * @author Katorly
     */
    init {
        filepath = File(plugin.dataFolder, path)
        file = File(filepath, name)
    }

    /**
     * Create the default config if the config does not exist.
     *
     */
    fun saveDefaultConfig() {
        if (!filepath!!.exists()) {
            val success = filepath!!.mkdirs()
            if (!success) Bukkit.getLogger()
                .severe("[" + plugin.name + "] Error creating the config. Please try again.")
        }
        if (!file!!.exists()) plugin.saveResource(path + name, false)
    }

    /**
     * Get values in the config.
     *
     * @return
     */
    fun getConfig(): FileConfiguration? {
        if (!filepath!!.exists()) saveDefaultConfig()
        if (config == null) reloadConfig()
        return config
    }

    /**
     * Reload the config.
     *
     */
    fun reloadConfig() {
        saveDefaultConfig()
        if (filepath == null) filepath = File(plugin.dataFolder, path)
        if (file == null) file = File(filepath, name)
        config = YamlConfiguration.loadConfiguration(file!!)
        val stream = plugin.getResource(name)
        if (stream != null) {
            val ymlFile = YamlConfiguration.loadConfiguration(InputStreamReader(stream))
            config?.setDefaults(ymlFile)
        }
    }

    /**
     * Save the config to apply changes.
     *
     */
    fun saveConfig() {
        try {
            config!!.save(file!!)
        } catch (t: Throwable) {
            t.printStackTrace()
            Bukkit.getLogger().severe("[" + plugin.name + "] Error saving the config. Please try again.")
        }
    }

    companion object {
        /**
         * Excute the function of saveConfig() and reloadConfig()
         *
         * @param config
         */
        fun save(config: Configer) {
            config.saveConfig()
            config.reloadConfig()
        }
    }
}