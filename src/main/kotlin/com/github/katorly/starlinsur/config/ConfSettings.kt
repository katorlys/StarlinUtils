/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.config

import com.github.katorly.starlinsur.havoc.primitives.boolean
import com.github.katorly.starlinsur.keepInventory
import com.github.katorly.starlinsur.keepLevel
import com.github.katorly.starlinsur.prefix
import com.github.katorly.starlinsur.spawn
import com.github.katorly.starlinsur.utils.save

/**
 * 将 settings.yml 中的一些值绑定到插件储存的变量中.
 * (只会绑定一些高频变量)
 *
 */
fun assignSettings() {
    conf.getString("prefix")?.let { prefix = conf.getString("prefix")!! }
    conf.getLocation("location.spawn")?.let { spawn = it } ?: run { conf.set("location.spawn", spawn) }

    keepInventory = conf["game.keepInventory"].boolean(true)
    keepLevel = conf["game.keepLevel"].boolean(true)

    config.save()
}