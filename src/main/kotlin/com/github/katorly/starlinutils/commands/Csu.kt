package com.github.katorly.starlinutils.commands

import com.github.katorly.starlinutils.ConfigHandler.prefix
import com.github.katorly.starlinutils.ConfigHandler.reloadConfig
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
            sm(sender, "${prefix}用法: /su <参数>.")
        }
        incorrectCommand { sender, context, index, state ->
            sm(sender, "${prefix}用法: /su <参数>.")
        }
    }

    @CommandBody(optional = true)
    val reload = subCommand {
        execute<ProxyCommandSender> { sender, context, arg ->
            if (sender.isOp) {
                reloadConfig()
                sm(sender, "${prefix}成功重载插件配置.")
            } else {
                sm(sender, "${prefix}没有权限!")
            }
        }
    }
}