/*
 * Copyright (c) 2020-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils

import com.github.katorly.starlinutils.ConfigHandler.conf
import com.github.katorly.starlinutils.ConfigHandler.reloadConfig
import com.github.katorly.starlinutils.tools.PlayTime.initPlayTime
import com.github.katorly.starlinutils.tools.PlayTime.settlePlayTime
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getScheduler
import org.bukkit.NamespacedKey
import org.bukkit.event.HandlerList.unregisterAll
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.getDataFolder
import taboolib.common.platform.function.info
import taboolib.common.platform.function.submitAsync
import taboolib.expansion.setupPlayerDatabase
import taboolib.platform.BukkitPlugin
import java.io.File


object StarlinUtils : Plugin() {
    val plugin by lazy { BukkitPlugin.getInstance() }
    var serverClosing = false
    val recipeKeys: MutableList<NamespacedKey> = ArrayList()

    override fun onEnable() {
        reloadConfig()
        if (conf.getBoolean("playtime.enable-mysql")) {
            setupPlayerDatabase(conf.getConfigurationSection("playtime.mysql")!!)
        } else {
            setupPlayerDatabase(File(getDataFolder(), "data.db"))
        }
        RecipeHandler.registerConcreteRecipe()
        if (plugin.server.pluginManager.getPlugin("LuckPerms") != null) info("[StarlinUtils] 检测到 LuckPerms, 现在可以使用 /setupfly 指令来初始化飞行权限组 (如果还没初始化的话).")
        info("[StarlinUtils] 仓库: https://github.com/katorlys/StarlinUtils")
        info("[StarlinUtils] StarlinUtils 已加载! 为 星林宇宙 而做.")

        // PlayTime 相关功能
        info("[StarlinUtils] 初始化在线玩家游玩时间中......")
        submitAsync(true) {
            if (Bukkit.getOnlinePlayers().isNotEmpty()) {
                Bukkit.getOnlinePlayers().forEach {
                    initPlayTime(it)
                }
            }
        }
        info("[StarlinUtils] 初始化在线玩家游玩时间完成!")
    }

    override fun onDisable() {
        // PlayTime 相关功能
        info("[StarlinUtils] 保存玩家游玩时间中......")
        if (Bukkit.getOnlinePlayers().isNotEmpty()) {
            Bukkit.getOnlinePlayers().forEach {
                settlePlayTime(it)
            }
        }
        info("[StarlinUtils] 保存玩家游玩时间完成!")

        reloadConfig()
        unregisterAll()
        getScheduler().cancelTasks(plugin)
        recipeKeys.forEach(Bukkit::removeRecipe)
        recipeKeys.clear()
        info("[StarlinUtils] StarlinUtils 已卸载!")
    }
}