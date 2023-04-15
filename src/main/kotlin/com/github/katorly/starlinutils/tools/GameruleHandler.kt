/*
 * Copyright (c) 2020-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.tools

import com.github.katorly.starlinutils.ConfigHandler.grs
import org.bukkit.Bukkit
import org.bukkit.World


object GameruleHandler {
    /**
     * 执行游戏规则自动设置.
     *
     */
    fun setGamerule(): Int {
        var changed = 0
        for (key in grs.getKeys(false)) {
            if (grs.getString("${key}.value") != null) {
                val value: String = grs.getString("${key}.value")!!
                var world: MutableList<World> = ArrayList()
                if (grs.getString("${key}.worlds") == "*") {
                    world = Bukkit.getWorlds()
                } else {
                    grs.getString("${key}.worlds")?.filter { !it.isWhitespace() }?.split(",")?.forEach {
                        if (Bukkit.getWorld(it) != null) world.add(Bukkit.getWorld(it)!!)
                    }
                }
                if (world.isEmpty()) break
                world.forEach {
                    if (it.isGameRule(key) && it.getGameRuleValue(key)!!::class == value::class
                        && it.getGameRuleValue(key) != value
                    ) {
                        it.setGameRuleValue(key, value)
                        changed++
                    }
                }
            }
        }

        /**
         * 本来下面这段代码是给安装了 Multiverse-Core 多世界插件的服务器用的,
         * 结果该插件居然没提供 gamerule 相关的 API，那就算了.
         *
         * 代码中的 PropertyValue 均不是 gamerule 的意思.
         *
         */
        /*if (Bukkit.getPluginManager().getPlugin("Multiverse-Core") != null) {
            val mvcore = Bukkit.getServer().pluginManager.getPlugin("Multiverse-Core") as MultiverseCore?
            val wm = mvcore!!.mvWorldManager
            for (key in grs.getKeys(false)) {
                if (grs.getString("${key}.value") != null) {
                    val value: String = grs.getString("${key}.value")!!
                    val world: MutableList<MultiverseWorld> = ArrayList()
                    if (grs.getString("${key}.worlds") == "*") {
                        wm.mvWorlds.forEach {
                            world.add(it)
                        }
                    } else {
                        grs.getString("${key}.worlds")?.filter { !it.isWhitespace() }?.split(",")?.forEach {
                            if (wm.getMVWorld(it) != null) world.add(wm.getMVWorld(it))
                        }
                    }
                    if (world.isEmpty()) break
                    world.forEach {
                        try {
                            if (it.getPropertyValue(key)::class == value::class && it.getPropertyValue(key) != value) {
                                it.setPropertyValue(key, value)
                            }
                        } catch (_: Throwable) { // 用于捕获 Multiverse-Core's 的 PropertyDoesNotExistException 异常, 当 Property 不存在的时候. 不要直接捕获它提供的那个异常, 不然这段代码即使未启用(即未安装 Multiverse-Core 插件)时程序无法运行!
                            info(it.name)
                        }
                    }
                }
            }
        }*/

        return changed
    }
}