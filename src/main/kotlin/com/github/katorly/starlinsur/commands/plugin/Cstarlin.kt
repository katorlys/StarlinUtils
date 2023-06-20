/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.commands.plugin

import com.github.katorly.starlinsur.havoc.msg.sm
import com.github.katorly.starlinsur.prefix
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor

class Cstarlin : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (command.name.equals("starlin", true)) {
            if (sender.isOp) {
                if (args.size == 1) {
                    when (args[0]) {
                        "debug" -> Cdebug(sender, command, label, args)
                        "reload" -> Creload(sender, command, label, args)
                        else -> sender.sm("${prefix}用法: &f/starlin {参数}")
                    }
                } else {
                    sender.sm("${prefix}用法: &f/starlin {参数}")
                }
            } else {
                sender.sm("${prefix}您没有权限这么做!")
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
        return if (args.size == 1) {
            listOf("debug", "reload")
        } else {
            null
        }
    }
}