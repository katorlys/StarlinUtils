/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.config

import com.github.katorly.starlinsur.StarlinSMP.Companion.plugin
import com.github.katorly.starlinsur.utils.YmlConfig

lateinit var config: YmlConfig
val conf by lazy { config.getConfig() }

fun regConfig() {
    config = YmlConfig(plugin, "settings.yml")
    config.saveDefaultConfig()

    reloadConfigs()
}

fun reloadConfigs() {
    config.reloadConfig()
    assignSettings()
}