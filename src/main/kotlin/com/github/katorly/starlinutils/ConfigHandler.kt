/*
 * Copyright (c) 2020-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils

import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration

object ConfigHandler {
    @Config("config.yml")
    lateinit var config: Configuration

    var conf: MutableMap<String, String> = HashMap()
    var confl: MutableMap<String, List<String>> = HashMap()
    lateinit var prefix: String

    @Config("gamerule.yml")
    lateinit var grs: Configuration

    /**
     * 重载所有配置文件.
     *
     */
    fun reloadConfig() {
        config.reload()
        grs.reload()
        cache()
    }

    /**
     * 缓存配置文件, 目前被缓存的有:
     * config.yml
     *
     */
    private fun cache() {
        conf.clear()
        confl.clear()
        for (key in config.getKeys(true)) {
            if (config.isString(key) && config.getString(key) != null) conf[key] = config.getString(key)!!
            else if (config.getStringList(key).isNotEmpty()) confl[key] = config.getStringList(key)
        }
        prefix = conf["prefix"] ?: "&b&l星林宇宙 &r&7>> &7"
    }
}