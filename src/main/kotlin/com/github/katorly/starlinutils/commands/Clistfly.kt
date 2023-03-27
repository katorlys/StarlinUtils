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
            val cmd: String? = conf["fly.list-fly"]
            sender.performCommand(cmd!!)
        }
        incorrectCommand { sender, context, index, state ->
            sm(sender, "${prefix}用法: /listfly.")
        }
    }
}