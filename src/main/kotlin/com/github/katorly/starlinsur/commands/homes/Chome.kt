/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.commands.homes

import com.github.katorly.starlinsur.havoc.msg.sm
import com.github.katorly.starlinsur.playerHomes
import com.github.katorly.starlinsur.prefix
import com.github.katorly.starlinsur.utils.tp.teleportSync
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import java.util.*

class Chome : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name.equals("home", true)) {
            if (sender is Player) {
                if (args.isNotEmpty()) {
                    val p: Player = sender
                    val u: UUID = p.uniqueId
                    if (playerHomes[u]?.containsKey(args[0]) == true) {
                        p.teleportSync(playerHomes[u]!![args[0]]!!)
                        p.sm("${prefix}传送到 &f${args[0]} &7家.")
                    } else {
                        p.sm("${prefix}&f${args[0]} &7不存在!")
                    }
                } else {
                    sender.sm("${prefix}用法: &f/home {家名}")
                }
            } else {
                sender.sm("${prefix}只有玩家才能回到自己的家!")
            }
        }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String>? {
        if (args.size == 1) {
            playerHomes[(sender as? Player)?.uniqueId]?.let { return it.keys.toList() } ?: run { return null }
        } else {
            return null
        }
    }
}