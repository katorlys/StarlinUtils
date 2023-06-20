/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.havoc.msg

import com.github.katorly.starlinsur.havoc.string.multireplace
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable

/**
 * HavocBukkit
 * havocbukkit.msg.Messager
 *
 * @author Katorly
 * @since 0.0.0
 */

/**
 * Replace "&" with "§" to apply colors to messages.
 *
 * @return [String]
 */
fun String.color(): String {
    return this.multireplace(
        "&" to "§",
        "§§" to "&"
    )
}

/**
 * Send message to a player.
 *
 * @param message
 */
fun Player.sm(message: String) {
    this.sendMessage(message.color())
}

fun CommandSender.sm(message: String) {
    this.sendMessage(message.color())
}

/**
 * Send list message to a player.
 *
 * @param messageList
 */
fun Player.sl(messageList: List<String>) {
    for (message in messageList) this.sm(message)
}

fun CommandSender.sl(messageList: List<String>) {
    for (message in messageList) this.sm(message)
}

/**
 * Send message to all online players.
 *
 * @param message
 */
fun bm(message: String) {
    for (player in Bukkit.getOnlinePlayers()) {
        player.sm(message)
    }
}

/**
 * Send message to console and all online players.
 *
 * @param message
 */
fun bma(message: String) {
    Bukkit.broadcastMessage(message.color())
}

/**
 * Send title to a player.
 *
 * @param title
 * @param subtitle
 * @param fadeIn time in seconds for titles to fade in. Defaults to 0.5.
 * @param stay time in seconds for titles to stay. Defaults to 2.
 * @param fadeOut time in seconds for titles to fade out. Defaults to 1.
 */
fun Player.st(title: String, subtitle: String, fadeIn: Number = 0.5, stay: Number = 2, fadeOut: Number = 1) {
    this.sendTitle(title.color(), subtitle.color(), (fadeIn.toDouble() * 20).toInt(), (stay.toDouble() * 20).toInt(), (fadeOut.toDouble() * 20).toInt())
}

/**
 * Send title to all online players.
 *
 * @param title
 * @param subtitle
 * @param fadeIn time in seconds for titles to fade in. Defaults to 0.5.
 * @param stay time in seconds for titles to stay. Defaults to 2.
 * @param fadeOut time in seconds for titles to fade out. Defaults to 1.
 */
fun bt(title: String, subtitle: String, fadeIn: Number = 0.5, stay: Number = 2, fadeOut: Number = 1) {
    for (player in Bukkit.getOnlinePlayers()) {
        player.st(title, subtitle, fadeIn, stay, fadeOut)
    }
}

/**
 * Send boss bar message to a player.
 *
 * @param plugin the plugin instance.
 * @param message
 * @param seconds time in seconds for boss bar to display.
 * @param color the color of the boss bar.
 * @param style the style of the boss bar.
 */
fun Player.sb(plugin: JavaPlugin, message: String, seconds: Number = 1, color: BarColor = BarColor.BLUE, style: BarStyle = BarStyle.SOLID) {
    val bossBar = Bukkit.createBossBar(message, color, style)
    bossBar.addPlayer(this)
    object : BukkitRunnable() {
        override fun run() {
            bossBar.removePlayer(this@sb)
            cancel()
        }
    }.runTaskLater(plugin, (seconds.toDouble() * 20).toLong())
}

/**
 * Send boss bar message to all players.
 *
 * @param plugin the plugin instance.
 * @param message
 * @param seconds time in seconds for boss bar to display.
 * @param color the color of the boss bar.
 * @param style the style of the boss bar.
 */
fun bb(plugin: JavaPlugin, message: String, seconds: Number = 1, color: BarColor = BarColor.BLUE, style: BarStyle = BarStyle.SOLID) {
    val bossBar = Bukkit.createBossBar(message, color, style)
    for (player in Bukkit.getOnlinePlayers()) player.sb(plugin, message, seconds, color, style)
}

/**
 * Send actionbar message to a player.
 *
 * @param message
 */
fun Player.sa(message: String) {
    this.spigot().sendMessage(
        ChatMessageType.ACTION_BAR, *TextComponent.fromLegacyText(
            message.color()
        )
    )
}

/**
 * Send actionbar message to all online players.
 *
 * @param message
 */
fun ba(message: String) {
    for (player in Bukkit.getOnlinePlayers()) player.sa(message)
}