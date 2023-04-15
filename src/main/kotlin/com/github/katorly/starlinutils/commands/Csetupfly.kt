/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.commands

import com.github.katorly.starlinutils.ConfigHandler.prefix
import com.github.katorly.starlinutils.StarlinUtils
import com.github.katorly.starlinutils.tools.FlyManager
import com.github.katorly.starlinutils.utils.Messager.sm
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand

@CommandHeader("setupfly")
object Csetupfly {
    @CommandBody
    val main = mainCommand {
        execute<ProxyCommandSender> { sender, context, arg ->
            if (sender.isOp) {
                if (StarlinUtils.islp()) {
                    FlyManager.setup()
                    sm(sender, "${prefix}成功初始化飞行权限组.")
                } else {
                    sm(sender, "${prefix}服务器未安装 LuckPerms 插件, 无法自动初始化飞行权限组.")
                }
            }
        }
        incorrectCommand { sender, context, index, state ->
            sm(sender, "${prefix}用法: /listfly.")
        }
    }
}