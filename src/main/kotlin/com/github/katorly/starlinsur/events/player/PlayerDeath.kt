/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.events.player

import com.github.katorly.starlinsur.keepInventory
import com.github.katorly.starlinsur.keepLevel
import com.github.katorly.starlinsur.playerDeathPoints
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class PlayerDeath : Listener {
    @EventHandler
    fun onPlayerDeath(e: PlayerDeathEvent) {
        e.keepInventory = keepInventory
        e.keepLevel = keepLevel
        playerDeathPoints[e.entity.uniqueId] = e.entity.location
    }
}