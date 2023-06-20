/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur

import com.github.katorly.starlinsur.StarlinSMP.Companion.plugin
import org.bukkit.Location
import java.util.*

/* 插件游戏内消息前缀 */
var prefix: String = com.github.katorly.starlinsur.havoc.prefix

/* 服务器主城位置 */
var spawn: Location = plugin.server.worlds.first().spawnLocation

/* 玩家的家 */
val playerHomes: MutableMap<UUID, MutableMap<String, Location>> = HashMap()

/* 玩家(临时)死亡点 */
val playerDeathPoints: MutableMap<UUID,Location> = HashMap()

/* 游戏体验相关设置 */
var keepInventory: Boolean = true
var keepLevel: Boolean = true