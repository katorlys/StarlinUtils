/*
 * Copyright (c) 2020-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.commands

import com.github.katorly.starlinutils.ConfigHandler.prefix
import com.github.katorly.starlinutils.ConfigHandler.reloadConfig
import com.github.katorly.starlinutils.tools.GameruleHandler.setGamerule
import com.github.katorly.starlinutils.utils.Messager.sm
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.command.subCommand

/**
 * 插件主命令.
 *
 */
@CommandHeader("starlinutils", aliases = ["starlinutil", "su"])
object Csu {
    @CommandBody
    val main = mainCommand {
        execute<ProxyCommandSender> { sender, context, arg ->
            sm(sender, "${prefix}用法: /su <二级指令>.")
        }
        incorrectCommand { sender, context, index, state ->
            sm(sender, "${prefix}用法: /su <二级指令>.")
        }
    }

    @CommandBody(optional = true, aliases = ["a", "auto"])
    val autoset = subCommand {
        execute<ProxyCommandSender> { sender, context, arg ->
            if (sender.isOp) {
                sm(sender, "${prefix}还没做! 急什么!")
                TODO()
                sm(sender, "${prefix}成功为服务器设置预设设置.")
            } else sm(sender, "${prefix}没有权限!")
        }
    }

    @CommandBody(optional = true, aliases = ["g", "gr", "grm", "grms"])
    val gamerule = subCommand {
        execute<ProxyCommandSender> { sender, context, arg ->
            if (sender.isOp) sm(sender, "${prefix}成功执行游戏规则自动设置, 共更改 &f${setGamerule()} &7处.")
            else sm(sender, "${prefix}没有权限!")
        }
    }

    @CommandBody(optional = true, aliases = ["r"])
    val reload = subCommand {
        execute<ProxyCommandSender> { sender, context, arg ->
            if (sender.isOp) {
                reloadConfig()
                sm(sender, "${prefix}成功重载插件配置.")
            } else sm(sender, "${prefix}没有权限!")
        }
    }
}