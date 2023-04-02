/*
 * Copyright (c) 2020-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.gamerule

import com.github.katorly.starlinutils.ConfigHandler.grs
//import com.onarandombox.MultiverseCore.MultiverseCore
//import com.onarandombox.MultiverseCore.api.MultiverseWorld
import org.bukkit.Bukkit
import org.bukkit.World


object GameruleHandler {
    /**
     * 执行游戏规则自动设置.
     *
     */
    fun setGamerule(): Int {
        var changed = 0

        /**
         * 若安装 Multiverse-Core, 则使用其提供的 API.
         *
         */
        /*if (Bukkit.getPluginManager().getPlugin("Multiverse-Core") != null) {
            val mvcore = Bukkit.getServer().pluginManager.getPlugin("Multiverse-Core") as MultiverseCore?
            val wm = mvcore!!.mvWorldManager
            for (key in grs.getKeys(false)) {
                if (grs.getString("${key}.value") != null) {
                    val value: String = grs.getString("${key}.value")!!
                    lateinit var world: MutableList<MultiverseWorld>
                    grs.getString("${key}.worlds")?.filter { !it.isWhitespace() }?.split(",")?.forEach {
                        if (wm.getMVWorld(it) != null) world.add(wm.getMVWorld(it))
                    }
                    if (world.isEmpty()) world = wm.mvWorlds as MutableList<MultiverseWorld>
                    world.forEach {
                        try {
                            if (it.getPropertyValue(key)::class == value::class && it.getPropertyValue(key) != value) {
                                it.setPropertyValue(key, value)
                            }
                        } catch (_: Property) {
                        }
                    }
                }
            }
        }*/
        /**
         * 若未安装 Multiverse-Core, 则使用 Bukkit API.
         *
         */
//        else {
            for (key in grs.getKeys(false)) {
                if (grs.getString("${key}.value") != null) {
                    val value: String = grs.getString("${key}.value")!!
                    lateinit var world: MutableList<World>
                    grs.getString("${key}.worlds")?.filter { !it.isWhitespace() }?.split(",")?.forEach {
                        if (Bukkit.getWorld(it) != null) world.add(Bukkit.getWorld(it)!!)
                    }
                    if (world.isEmpty()) world = Bukkit.getWorlds()
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
//        }
        return changed
    }
}