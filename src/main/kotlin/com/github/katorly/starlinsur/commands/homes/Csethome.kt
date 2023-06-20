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
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player
import java.util.*

class Csethome : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name.equals("sethome", true)) {
            if (sender is Player) {
                if (args.isNotEmpty()) {
                    val p: Player = sender
                    val u: UUID = p.uniqueId
                    if (!playerHomes.contains(u)) playerHomes[u] = HashMap()
                    val homes = playerHomes[u]
                    if (!homes!!.contains(args[0])) {
                        homes[args[0]] = p.location
                        p.sm("${prefix}成功设置家 &f${args[0]}&7.")
                    } else {
                        p.sm("${prefix}家名称: &f${args[0]} &7已存在! 请先删除该家或更换名称.")
                    }
                } else {
                    sender.sm("${prefix}用法: &f/sethome {家名}")
                }
            } else {
                sender.sm("${prefix}只有玩家才能设置家!")
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
        return null
    }
}