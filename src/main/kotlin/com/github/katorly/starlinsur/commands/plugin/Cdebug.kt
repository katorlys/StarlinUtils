/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.commands.plugin

import com.github.katorly.starlinsur.havoc.Logger.debug
import com.github.katorly.starlinsur.havoc.Logger.debugMode
import com.github.katorly.starlinsur.havoc.msg.sm
import com.github.katorly.starlinsur.prefix
import org.bukkit.command.Command
import org.bukkit.command.CommandSender

fun Cdebug(sender: CommandSender, command: Command, label: String, args: Array<out String>) {
    debugMode()
    sender.sm("${prefix}将调试模式设置为 ${debug}.")
}