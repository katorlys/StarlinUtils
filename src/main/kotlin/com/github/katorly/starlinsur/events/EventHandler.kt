/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.events

import com.github.katorly.starlinsur.StarlinSMP.Companion.plugin
import com.github.katorly.starlinsur.events.player.PlayerDeath
import com.github.katorly.starlinsur.events.player.PlayerJoin
import com.github.katorly.starlinsur.events.player.PlayerQuit
import org.bukkit.Bukkit
import org.bukkit.event.Listener

val events: Array<Listener> = arrayOf(
    PlayerDeath(),
    PlayerJoin(),
    PlayerQuit()
)

fun regEvents() {
    if (events.isNotEmpty()) {
        for (event in events) {
            Bukkit.getPluginManager().registerEvents(event, plugin)
        }
    }
}