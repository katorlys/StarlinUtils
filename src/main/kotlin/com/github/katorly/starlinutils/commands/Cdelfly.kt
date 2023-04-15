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
import com.github.katorly.starlinutils.StarlinUtils
import com.github.katorly.starlinutils.tools.FlyManager
import com.github.katorly.starlinutils.utils.Messager.sm
import net.luckperms.api.model.group.Group
import net.luckperms.api.model.user.User
import net.luckperms.api.node.types.InheritanceNode
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.mainCommand
import taboolib.common.platform.function.getProxyPlayer
import taboolib.common.platform.function.onlinePlayers
import java.util.*

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
                    if (StarlinUtils.islp()) {
                        // 谁敢冲突 UUID 那就是谁自己的事, 这个 Deprecation 我不管了
                        val t: UUID =
                            getProxyPlayer(arg)?.uniqueId ?: StarlinUtils.plugin.server.getOfflinePlayer(arg).uniqueId
                        if (t.toString() != "") {
                            val p: ProxyPlayer = getProxyPlayer(t)!!
                            FlyManager.setup()
                            val g: Group = StarlinUtils.lp.groupManager.getGroup("fly")!!
                            if (p.hasPermission("group.$g")) {
                                StarlinUtils.lp.userManager.modifyUser(p.uniqueId) { u: User ->
                                    u.data().add(InheritanceNode.builder(g).build())
                                    sm(sender, "${prefix}成功移除${p.name}的飞行权限.")
                                }
                            } else sm(sender, "${prefix}${p.name}并未拥有飞行权限!")
                        } else sm(sender, "${prefix}玩家不存在!")
                    } else {
                        val cmd: String? = ConfigHandler.conf.getString("fly.del-fly")?.replace("<player>", arg)
                        if (cmd != null) sender.performCommand(cmd)
                        else sm(sender, "${prefix}无法给予玩家飞行权限. 原因: 既未安装 LuckPerms, 也未设置相应指令.")
                    }
                }
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