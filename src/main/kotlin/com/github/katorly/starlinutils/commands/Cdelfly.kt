/*
 * Copyright (c) 2020-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.commands

import com.github.katorly.starlinutils.ConfigHandler
import com.github.katorly.starlinutils.ConfigHandler.prefix
import com.github.katorly.starlinutils.StarlinUtils.plugin
import com.github.katorly.starlinutils.tools.FlyManager
import com.github.katorly.starlinutils.utils.Messager.sm
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.function.onlinePlayers

/**
 * 移除一名玩家的飞行权限.
 *
 */
@CommandHeader("delfly")
object Cdelfly {
    @CommandBody
    val main = mainCommand {
        dynamic(optional = true) {
            suggestion<ProxyCommandSender> { sender, context ->
                onlinePlayers().map { it.name }
            }
            execute<ProxyCommandSender> { sender, context, arg ->
                if (sender.isOp) {
                    if (plugin.server.pluginManager.getPlugin("LuckPerms") != null) {
                        FlyManager.delfly(arg, sender)
                    } else {
                        val cmd: String? = ConfigHandler.conf.getString("fly.del-fly")?.replace("<player>", arg)
                        if (cmd != null) sender.performCommand(cmd)
                        else sm(sender, "${prefix}无法给予玩家飞行权限. 原因: 既未安装 LuckPerms, 也未设置相应指令.")
                    }
                } else sm(sender, "${prefix}没有权限!")
            }
        }
        execute<ProxyCommandSender> { sender, context, arg ->
            sm(sender, "${prefix}用法: /delfly <玩家ID>.")
        }
        incorrectCommand { sender, context, index, state ->
            sm(sender, "${prefix}用法: /delfly <玩家ID>.")
        }
    }
}