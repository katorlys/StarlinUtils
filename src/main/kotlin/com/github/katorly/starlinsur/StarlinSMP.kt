/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur

import com.github.katorly.starlinsur.commands.regCommands
import com.github.katorly.starlinsur.config.regConfig
import com.github.katorly.starlinsur.config.reloadConfigs
import com.github.katorly.starlinsur.database.closeDB
import com.github.katorly.starlinsur.database.initDB
import com.github.katorly.starlinsur.database.playerdata.initPlayerData
import com.github.katorly.starlinsur.database.playerdata.settlePlayerData
import com.github.katorly.starlinsur.events.regEvents
import com.github.katorly.starlinsur.havoc.delay
import com.github.katorly.starlinsur.havoc.info
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getScheduler
import org.bukkit.event.HandlerList.unregisterAll
import org.bukkit.plugin.java.JavaPlugin

class StarlinSMP : JavaPlugin() {
    companion object {
        lateinit var plugin: StarlinSMP
            private set
    }

    override fun onEnable() {
        plugin = this
        regConfig()
        initDB()
        regCommands()
        regEvents()
        delay(0, true) {
            if (Bukkit.getOnlinePlayers().isNotEmpty()) {
                Bukkit.getOnlinePlayers().forEach { initPlayerData(it) }
            }
            info("在线玩家统计数据初始化完成")
        }
        info("仓库: https://github.com/katorlys/StarlinUtils")
        info("StarlinSur 已加载! 为 星林宇宙 生存服务器 而做.")
    }

    override fun onDisable() {
        if (Bukkit.getOnlinePlayers().isNotEmpty()) {
            Bukkit.getOnlinePlayers().forEach { settlePlayerData(it) }
        }
        info("在线玩家统计信息结算完成")
        unregisterAll(this)
        closeDB()
        reloadConfigs()
        getScheduler().cancelTasks(this)
        info("StarlinSur 已卸载!")
    }
}