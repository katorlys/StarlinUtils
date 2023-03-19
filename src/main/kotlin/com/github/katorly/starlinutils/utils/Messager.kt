package com.github.katorly.starlinutils.utils

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import java.util.*

/**
 * Spigot-Messager (https://github.com/katorlys/Spigot-MessageSender)
 * Copyright (c) 2021-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

class Messager {
    companion object {
        /**
         * Replace "&" with "§" to fix color messages.
         *
         * @param string
         * @return
         */
        fun color(string: String): String {
            return Objects.requireNonNull(string).replace("&", "§").replace("§§", "&")
        }

        /**
         * Send message to a player.
         *
         * @param player
         * @param message
         */
        fun sm(player: Player, message: String) {
            player.sendMessage(color(message))
        }

        /**
         * Send message to a command sender.
         *
         * @param sender
         * @param message
         */
        fun srm(sender: CommandSender, message: String) {
            sender.sendMessage(color(message))
        }

        /**
         * Send list message to a player.
         *
         * @param player
         * @param messagelist
         */
        fun sl(player: Player, messagelist: List<String>) {
            for (message in messagelist) sm(player, message)
        }

        /**
         * Send list message to a command sender.
         *
         * @param sender
         * @param messagelist
         */
        fun srl(sender: CommandSender, messagelist: List<String>) {
            for (message in messagelist) srm(sender, message)
        }

        /**
         * Send message to all online players.
         *
         * @param message
         */
        fun bm(message: String) {
            for (player in Bukkit.getOnlinePlayers()) {
                player.sendMessage(color(message))
            }
        }

        /**
         * Send message to console and all online players.
         *
         * @param message
         */
        fun bma(message: String) {
            Bukkit.broadcastMessage(color(message))
        }

        /**
         * Send title to a player.
         *
         * @param player
         * @param title
         * @param subtitle
         */
        fun st(player: Player, title: String, subtitle: String) {
            player.sendTitle(color(title), color(subtitle), 10, 40, 20) // Show title 2s
        }

        /**
         * Send title to all online players.
         *
         * @param title
         * @param subtitle
         */
        fun bt(title: String, subtitle: String) {
            for (player in Bukkit.getOnlinePlayers()) {
                player.sendTitle(color(title), color(subtitle), 10, 40, 20) // Show title 2s
            }
        }

        /**
         * Send bossbar message to a player.
         *
         * @param plugin The plugin instance.
         * @param player
         * @param message
         * @param barColor The color of the bossbar.
         * @param seconds The number of seconds for the bossbar to display.
         */
        fun sb(plugin: JavaPlugin?, player: Player?, message: String?, barColor: BarColor?, seconds: Int) {
            val bossBar = Bukkit.createBossBar(message, barColor!!, BarStyle.SOLID)
            bossBar.addPlayer(player!!)
            object : BukkitRunnable() {
                override fun run() {
                    bossBar.removePlayer(player)
                    cancel()
                }
            }.runTaskLater(plugin!!, seconds * 20L)
        }

        /**
         * Send bossbar message to all players.
         *
         * @param plugin The plugin instance.
         * @param message
         * @param barColor The color of the bossbar.
         * @param seconds The number of seconds for the bossbar to display.
         */
        fun bb(plugin: JavaPlugin?, message: String?, barColor: BarColor?, seconds: Int) {
            val bossBar = Bukkit.createBossBar(message, barColor!!, BarStyle.SOLID)
            for (player in Bukkit.getOnlinePlayers()) bossBar.addPlayer(player!!)
            object : BukkitRunnable() {
                override fun run() {
                    bossBar.removeAll()
                    cancel()
                }
            }.runTaskLater(plugin!!, seconds * 20L)
        }

        /**
         * Send actionbar message to a player.
         *
         * @param player
         * @param message
         */
        fun sa(player: Player, message: String) {
            player.spigot().sendMessage(
                ChatMessageType.ACTION_BAR, *TextComponent.fromLegacyText(
                    color(message)
                )
            )
        }

        /**
         * Send actionbar message to all online players.
         *
         * @param message
         */
        fun ba(message: String) {
            for (player in Bukkit.getOnlinePlayers()) {
                player.spigot().sendMessage(
                    ChatMessageType.ACTION_BAR, *TextComponent.fromLegacyText(
                        color(message)
                    )
                )
            }
        }
    }
}