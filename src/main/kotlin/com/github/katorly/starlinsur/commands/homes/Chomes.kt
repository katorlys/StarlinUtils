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

class Chomes : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name.equals("homes", true)) {
            if (sender is Player) {
                val p: Player = sender
                val u: UUID = p.uniqueId
                if (playerHomes[u]?.isNotEmpty() == true) {
                    val homes = playerHomes[u]
                    p.sm("${prefix}您共设置了${homes!!.size}个家, 它们分别是:\n  &f${homes.keys.joinToString("&7, &f")}.")
                } else {
                    p.sm("${prefix}您还没有设置家. 使用 &f/sethome {家名} &7来设置一个新的家.")
                }
            } else {
                sender.sm("${prefix}只有玩家才能列出自己的所有家!")
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