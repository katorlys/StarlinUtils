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
    @Config("config.yml", true, true)
    lateinit var conf: Configuration

    lateinit var prefix: String

    @Config("gamerule.yml", true, true)
    lateinit var grs: Configuration

    /**
     * 重载所有配置文件.
     *
     */
    fun reloadConfig() {
        conf.reload()
        grs.reload()
        cache()
    }

    /**
     * 缓存配置文件, 目前被缓存的有:
     * config.yml
     *
     */
    private fun cache() {
        prefix = conf.getString("prefix") ?: "&b&l星林宇宙 &r&7>> &7"
    }
}