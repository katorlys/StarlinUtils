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
import com.github.katorly.starlinsur.playerDeathPoints
import com.github.katorly.starlinsur.prefix
import com.github.katorly.starlinsur.utils.tp.teleportSync
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

class Cback : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name.equals("back", true)) {
            if (sender is Player) {
                val p: Player = sender
                if (playerDeathPoints.containsKey(p.uniqueId)) {
                    p.teleportSync(playerDeathPoints[p.uniqueId]!!)
                } else {
                    // 传送玩家回主城
                    val l = p.location
                    if (l.isWorldLoaded && l.world != null) p.teleportSync(l.world!!.spawnLocation)
                    else p.sm("${prefix}&c错误: &7世界未加载. 或许你需要联系管理员加载该世界.")
                }
            } else {
                sender.sm("${prefix}只有玩家才能传送回上一位置!")
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