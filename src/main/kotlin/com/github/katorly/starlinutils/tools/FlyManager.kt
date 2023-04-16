/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.tools

import com.github.katorly.starlinutils.ConfigHandler.prefix
import com.github.katorly.starlinutils.StarlinUtils.plugin
import com.github.katorly.starlinutils.utils.Messager.sm
import net.luckperms.api.LuckPerms
import net.luckperms.api.model.group.Group
import net.luckperms.api.model.group.GroupManager
import net.luckperms.api.model.user.User
import net.luckperms.api.node.Node
import net.luckperms.api.node.matcher.NodeMatcher
import net.luckperms.api.node.types.InheritanceNode
import org.bukkit.Bukkit
import taboolib.common.platform.ProxyCommandSender
import taboolib.common.platform.ProxyPlayer
import taboolib.common.platform.function.getProxyPlayer
import java.util.*

// TODO 目前只有安装了 LuckPerms 才能使用飞行权限组管理
object FlyManager {
    private fun getLP(): LuckPerms? {
        return Bukkit.getServicesManager().getRegistration(LuckPerms::class.java)?.provider
    }

    /**
     * 若 fly 权限组不存在, 则自动创建 (初始化).
     *
     */
    fun setup() {
        if (getLP() != null) {
            val gm: GroupManager = getLP()!!.groupManager
            gm.modifyGroup("fly") { group: Group ->
                group.data().add(Node.builder("essentials.fly").value(true).build())
                group.data().add(Node.builder("essentials.fly.safelogin").value(true).build())
            }
        }
    }

    /**
     * 给予一名玩家飞行权限.
     *
     */
    fun givefly(arg: String, sender: ProxyCommandSender) {
        if (getLP() != null) {
            val lp: LuckPerms = getLP()!!
            // 谁敢冲突 UUID 那就是谁自己的事, 这个 Deprecation 我不管了
            val t: UUID = getProxyPlayer(arg)?.uniqueId ?: plugin.server.getOfflinePlayer(arg).uniqueId
            if (t.toString() != "") {
                val p: ProxyPlayer = getProxyPlayer(t)!!
                setup()
                val g: Group = lp.groupManager.getGroup("fly")!!
                if (!p.hasPermission("group.$g")) {
                    lp.userManager.modifyUser(p.uniqueId) { u: User ->
                        u.data().add(InheritanceNode.builder(g).build())
                        sm(sender, "${prefix}成功为${p.name}添加飞行权限.")
                    }
                } else sm(sender, "${prefix}${p.name}已拥有飞行权限!")
            } else sm(sender, "${prefix}玩家不存在!")
        }
    }

    /**
     * 列出所有拥有飞行权限的玩家.
     *
     */
    fun listfly(sender: ProxyCommandSender) {
        if (getLP() != null) {
            setup()
            val lp: LuckPerms = getLP()!!
            lp.userManager.searchAll(
                NodeMatcher.key(
                    InheritanceNode
                        .builder(lp.groupManager.getGroup("fly")!!).build()
                )
            ).thenAccept { map: Map<UUID, Collection<InheritanceNode?>?> ->
                val memberUniqueIds = map.keys
                sm(sender, ("${prefix}共 ${memberUniqueIds.size} 名玩家拥有飞行权限."))
                memberUniqueIds.forEach { key ->
                    sm(sender, "&7 ${plugin.server.getOfflinePlayer(key).name}")
                }
            }
        }
    }

    /**
     * 移除一名玩家的飞行权限.
     *
     */
    fun delfly(arg: String, sender: ProxyCommandSender) {
        if (getLP() != null) {
            val lp: LuckPerms = getLP()!!
            // 谁敢冲突 UUID 那就是谁自己的事, 这个 Deprecation 我不管了
            val t: UUID =
                getProxyPlayer(arg)?.uniqueId ?: plugin.server.getOfflinePlayer(arg).uniqueId
            if (t.toString() != "") {
                val p: ProxyPlayer = getProxyPlayer(t)!!
                setup()
                val g: Group = lp.groupManager.getGroup("fly")!!
                if (p.hasPermission("group.$g")) {
                    lp.userManager.modifyUser(p.uniqueId) { u: User ->
                        u.data().remove(InheritanceNode.builder(g).build())
                        sm(sender, "${prefix}成功移除${p.name}的飞行权限.")
                    }
                } else sm(sender, "${prefix}${p.name}并未拥有飞行权限!")
            } else sm(sender, "${prefix}玩家不存在!")
        }
    }
}