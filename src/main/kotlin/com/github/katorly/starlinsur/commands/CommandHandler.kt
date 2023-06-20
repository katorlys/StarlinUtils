/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.commands

import com.github.katorly.starlinsur.commands.homes.*
import com.github.katorly.starlinsur.commands.plugin.Cstarlin
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.command.TabCompleter

val commands: Array<String> = arrayOf(
    "back",
    "delhome",
    "home",
    "homes",
    "sethome",
    "spawn",

    "starlin"
)

val executors: Array<CommandExecutor> = arrayOf(
    Cback(),
    Cdelhome(),
    Chome(),
    Chomes(),
    Csethome(),
    Cspawn(),

    Cstarlin()
)

val completers: Array<TabCompleter> = arrayOf(
    Cback(),
    Cdelhome(),
    Chome(),
    Chomes(),
    Csethome(),
    Cspawn(),

    Cstarlin()
)

fun regCommands() {
    for (i in commands.indices) {
        Bukkit.getPluginCommand(commands[i])?.setExecutor(executors[i])
        Bukkit.getPluginCommand(commands[i])?.tabCompleter = completers[i]
    }

    /* val myCommand: Command = object : Command("mycommand") {
        override fun execute(sender: CommandSender, commandLabel: String, args: Array<out String>): Boolean {
            // Do something
            return true
        }
    }
    try {
        val commandMapField = Bukkit.getServer().javaClass.getDeclaredField("commandMap")
        commandMapField.isAccessible = true
        val commandMap = commandMapField[Bukkit.getServer()] as CommandMap
        commandMap.register("mycommand", myCommand)
    } catch (e: Exception) {
        e.printStackTrace()
        com.github.katorly.starlinsur.havoc.error("An error occurred while registering command: ${myCommand.name}")
    } */
}