/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.utils

/**
 * Spigot-Messager (https://github.com/katorlys/Spigot-MessageSender)
 * Copyright (c) 2021-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.entity.Player
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.function.submit
import taboolib.module.chat.colored
import taboolib.platform.util.sendActionBar

object Messager {
    /**
     * Send message to a ProxyCommandSender, a ProxyPlayer, or a Player.
     *
     * @param target
     * @param message
     */
    fun sm(target: ProxyCommandSender, message: String) {
        target.sendMessage(message.colored())
    }
    fun sm(target: ProxyPlayer, message: String) {
        target.sendMessage(message.colored())
    }
    fun sm(target: Player, message: String) {
        target.sendMessage(message.colored())
    }

    /**
     * Send message to all online players.
     * ! Bukkit Only
     *
     * @param message
     */
    fun bm(message: String) {
        for (target in Bukkit.getOnlinePlayers()) {
            sm(target, message)
        }
    }

    /**
     * Send list message to a ProxyCommandSender, a ProxyPlayer, or a Player.
     *
     * @param target
     * @param list
     */
    fun sl(target: ProxyCommandSender, list: List<String>) {
        for (message in list) sm(target, message)
    }
    fun sl(target: ProxyPlayer, list: List<String>) {
        for (message in list) sm(target, message)
    }
    fun sl(target: Player, list: List<String>) {
        for (message in list) sm(target, message)
    }

    /**
     * Send list message to all online players.
     * ! Bukkit Only
     *
     * @param list
     */
    fun bm(list: List<String>) {
        for (target in Bukkit.getOnlinePlayers()) {
            sl(target, list)
        }
    }

    /**
     * Send message to console and all online players.
     * ! Bukkit Only
     *
     * @param message
     */
    fun bma(message: String) {
        Bukkit.broadcastMessage(message.colored())
    }

    /**
     * Send title to a ProxyPlayer, or a Player.
     *
     * @param target
     * @param title
     * @param subtitle
     */
    fun st(target: ProxyPlayer, title: String, subtitle: String) {
        target.sendTitle(title.colored(), subtitle.colored(), 10, 40, 20) // Show title 2s
    }
    fun st(target: Player, title: String, subtitle: String) {
        target.sendTitle(title.colored(), subtitle.colored(), 10, 40, 20) // Show title 2s
    }

    /**
     * Send title to all online players.
     * ! Bukkit Only
     *
     * @param title
     * @param subtitle
     */
    fun bt(title: String, subtitle: String) {
        for (target in Bukkit.getOnlinePlayers()) {
            st(target, title.colored(), subtitle.colored()) // Show title 2s
        }
    }

    /**
     * Send bossbar message to a player.
     * ! Bukkit Only
     *
     * @param target
     * @param message
     * @param barColor The color of the bossbar.
     * @param seconds The number of seconds for the bossbar to display.
     */
    fun sb(target: Player, message: String, barColor: BarColor, seconds: Int) {
        val bossBar = Bukkit.createBossBar(message.colored(), barColor, BarStyle.SOLID)
        bossBar.addPlayer(target)
        submit(delay = seconds * 20L) {
            bossBar.removePlayer(target)
            cancel()
        }
    }

    /**
     * Send bossbar message to all players.
     * ! Bukkit Only
     *
     * @param message
     * @param barColor The color of the bossbar.
     * @param seconds The number of seconds for the bossbar to display.
     */
    fun bb(message: String, barColor: BarColor, seconds: Int) {
        val bossBar = Bukkit.createBossBar(message.colored(), barColor, BarStyle.SOLID)
        for (target in Bukkit.getOnlinePlayers()) bossBar.addPlayer(target)
        submit(delay = seconds * 20L) {
            bossBar.removeAll()
            cancel()
        }
    }

    /**
     * Send actionbar message to a ProxyPlayer, or a Player.
     *
     * @param target
     * @param message
     */
    fun sa(target: ProxyPlayer, message: String) {
        target.sendActionBar(message.colored())
    }
    fun sa(target: Player, message: String) {
        target.sendActionBar(message.colored())
    }

    /**
     * Send actionbar message to all online players.
     * ! Bukkit Only
     *
     * @param message
     */
    fun ba(message: String) {
        for (target in Bukkit.getOnlinePlayers()) {
            sa(target, message)
        }
    }
}