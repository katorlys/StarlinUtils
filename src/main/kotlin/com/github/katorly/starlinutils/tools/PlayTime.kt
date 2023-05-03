/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.tools

import org.bukkit.Statistic
import org.bukkit.entity.Player
import taboolib.expansion.DataContainer
import taboolib.expansion.getDataContainer
import taboolib.expansion.releaseDataContainer
import taboolib.expansion.setupDataContainer
import java.util.*

object PlayTime {
    val onlineTime: MutableMap<UUID, Long> = HashMap()

    private const val name = "name"
    private const val total = "total"
    private const val first = "first"
    private const val last = "last"

    /**
     * 记录玩家进服时间.
     * 若数据库中不存在该玩家的在线数据, 则创建并记录将该玩家之前的在线时间计入总在线时间.
     *
     */
    fun initPlayTime(p: Player) {
        onlineTime[p.uniqueId] = System.currentTimeMillis()
        p.setupDataContainer()
        init(p)
    }

    /**
     * 结算玩家的游玩时间.
     *
     * ? 若数据库中不存在该玩家的在线数据, 那就是使用者有问题.
     */
    fun settlePlayTime(p: Player) {
        val time: Int
        val c = Calendar.getInstance()
        val yrmon = "${c.get(Calendar.YEAR)}.${c.get(Calendar.MONTH)}"
        val d: DataContainer = p.getDataContainer()
        val keys: Set<String> = d.keys()
        if (onlineTime.containsKey(p.uniqueId)) {
            // 单位: 分钟(整数)
            time = (System.currentTimeMillis() / 1000 / 60 - onlineTime[p.uniqueId]!!).toInt()
            d[total] += time
            if (!keys.contains(yrmon))
                d[yrmon] = time
            else
                d[yrmon] += time
        }
        d[last] =
            "${yrmon}.${c.get(Calendar.MONTH)} ${c.get(Calendar.HOUR_OF_DAY)}:${c.get(Calendar.MINUTE)}:${c.get(Calendar.SECOND)}"
        p.releaseDataContainer()
    }

    /**
     * 初始化玩家 PlayTime 数据, 如果原本没有的话.
     * ! 调用前先确保玩家容器已被初始化 (setupDataContainer) !
     *
     * ! 特别注意这个鬼 Statistic.PLAY_ONE_MINUTE, 它实际上返回的是 TICK, 不过后面可能会修复, 要小心
     */
    private fun init(p: Player) {
        val d: DataContainer = p.getDataContainer()
        val keys: Set<String> = d.keys()
        if (!keys.contains(name))
            d[name] = p.name
        if (!keys.contains(first)) {
            // 若 firstPlayed = 0, 表明玩家是第一次进服.
            if (p.firstPlayed == 0.toLong()) {
                val c = Calendar.getInstance()
                d[first] =
                    "${c.get(Calendar.YEAR)}.${c.get(Calendar.MONTH)}.${c.get(Calendar.DAY_OF_MONTH)} ${c.get(Calendar.HOUR_OF_DAY)}:${
                        c.get(Calendar.MINUTE)
                    }:${c.get(Calendar.SECOND)}"
            } else {
                val c = Calendar.getInstance()
                c.timeInMillis = p.firstPlayed
                d[first] =
                    "${c.get(Calendar.YEAR)}.${c.get(Calendar.MONTH)}.${c.get(Calendar.DAY_OF_MONTH)} ${c.get(Calendar.HOUR_OF_DAY)}:${
                        c.get(Calendar.MINUTE)
                    }:${c.get(Calendar.SECOND)}"
            }
        }
        if (!keys.contains("total"))
        // 单位: 分钟(整数)
            d[total] = p.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20 / 60
    }
}