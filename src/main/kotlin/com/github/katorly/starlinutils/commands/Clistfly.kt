/*
 * Copyright (c) 2020-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.commands

import com.github.katorly.starlinutils.ConfigHandler.conf
import com.github.katorly.starlinutils.ConfigHandler.prefix
import com.github.katorly.starlinutils.utils.Messager.sm
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand


/**
 * 列出所有拥有飞行权限的玩家.
 *
 */
@CommandHeader("listfly")
object Clistfly {
    @CommandBody
    val main = mainCommand {
        execute<ProxyCommandSender> { sender, context, arg ->
            if (sender.isOp) {
                val cmd: String? = conf.getString("fly.list-fly")
                if (cmd != null) sender.performCommand(cmd)
                else sm(
                    sender,
                    "${prefix}无法列出所有拥有飞行权限的玩家. 原因: 既未安装 LuckPerms, 也未设置相应指令."
                )
            } else sm(sender, "${prefix}没有权限!")
        }
        incorrectCommand { sender, context, index, state ->
            sm(sender, "${prefix}用法: /listfly.")
        }
    }
}