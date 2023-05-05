/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.events

import com.github.katorly.starlinutils.tools.PlayTime.settlePlayTime
import org.bukkit.entity.Player
import org.bukkit.event.player.PlayerQuitEvent
import taboolib.common.platform.event.SubscribeEvent

object PlayerQuit {
    @SubscribeEvent
    fun onPlayerQuit(e: PlayerQuitEvent) {
        val p: Player = e.player

        // PlayTime 相关功能
        settlePlayTime(p)
    }
}