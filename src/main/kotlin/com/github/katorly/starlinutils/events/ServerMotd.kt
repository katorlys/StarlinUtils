/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.events

import com.github.katorly.starlinutils.ConfigHandler.conf
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.bukkit.event.server.ServerListPingEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common5.util.replace
import taboolib.module.chat.colored
import java.net.InetAddress

//@RuntimeDependency("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0") // 不需要这个
object ServerMotd {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onServerMotd(e: ServerListPingEvent) {
        val motd: String = conf.getString("motd")
            ?: "                &{#1085fb}&l星&{#198bfb}&l林&{#2291fb}&l宇&{#2b96fb}&l宙 &{#349cfc}&lS&{#3da2fc}&lt&{#46a8fc}&la&{#4faefc}&lr&{#57b3fc}&ll&{#60b9fc}&li&{#69bffc}&ln&{#72c5fc}&lW&{#7bcbfd}&lo&{#84d0fd}&lr&{#8dd6fd}&ll&{#96dcfd}&ld"
        e.motd = formatOther(motd, e.address.hostName, e.address, e.numPlayers, e.maxPlayers).tformat().colored()
    }

    /**
     * 格式化 Motd 参数中除时间以外的参数.
     *
     */
    private fun formatOther(
        motd: String,
        hostname: String,
        address: InetAddress,
        players: Int,
        maxplayers: Int
    ): String {
        return motd.replace(
            Pair("{hostname}", hostname),
            Pair("{address}", address.toString()),
            Pair("{players}", players.toString()),
            Pair("{maxplayers}", maxplayers.toString())
        )
    }

    /**
     * 格式化 Motd 中的时间参数.
     *
     */
    private fun String.tformat() = formatTime(this)
    private fun formatTime(motd: String): String {
        // 本来用的是 Calendar，但不是 Kotlin 的，就算了
        val c = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        return motd.replace(
            Pair("{day}", c.dayOfMonth),
            Pair("{month}", c.monthNumber),
            Pair("{year}", c.year),
            Pair("{hour}", c.hour),
            Pair("{minute}", c.minute),
            Pair("{second}", c.second)
        )

    }
}