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
import com.github.katorly.starlinutils.utils.Strings.multireplace
import org.bukkit.event.server.ServerListPingEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.module.chat.colored
import java.net.InetAddress
import java.util.*

object ServerMotd {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onServerMotd(e: ServerListPingEvent) {
        val motd: String = conf.getString("motd")
            ?: "                 &{#1085fb}&l星&{#198bfb}&l林&{#2291fb}&l宇&{#2b96fb}&l宙 &{#349cfc}&lS&{#3da2fc}&lt&{#46a8fc}&la&{#4faefc}&lr&{#57b3fc}&ll&{#60b9fc}&li&{#69bffc}&ln&{#72c5fc}&lW&{#7bcbfd}&lo&{#84d0fd}&lr&{#8dd6fd}&ll&{#96dcfd}&ld"
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
        return motd.multireplace(
            "{hostname}" to hostname,
            "{address}" to address.toString(),
            "{players}" to players.toString(),
            "{maxplayers}" to maxplayers.toString()
        )
    }

    /**
     * 格式化 Motd 中的时间参数.
     *
     */
    private fun String.tformat() = formatTime(this)
    private fun formatTime(motd: String): String {
        // 本来用的是 Kotlinx DateTime，但由于 Taboolib 的缘故必须打包进 jar 文件, 太大了, 就算了
        val c = Calendar.getInstance()
        return motd.multireplace(
            "{day}" to c.get(Calendar.DAY_OF_MONTH),
            "{month}" to c.get(Calendar.MONTH),
            "{year}" to c.get(Calendar.YEAR),
            "{hour}" to c.get(Calendar.HOUR_OF_DAY),
            "{minute}" to c.get(Calendar.MINUTE),
            "{second}" to c.get(Calendar.SECOND)
        )

    }
}