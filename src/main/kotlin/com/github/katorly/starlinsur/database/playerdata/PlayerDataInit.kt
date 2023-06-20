/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.database.playerdata

import com.github.katorly.starlinsur.database.db
import com.github.katorly.starlinsur.havoc.time.formatTime
import com.github.katorly.starlinsur.playerDeathPoints
import com.github.katorly.starlinsur.playerHomes
import kotlinx.serialization.json.Json
import org.bukkit.Statistic
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insertIgnore
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

val joinTime: MutableMap<UUID, Long> = HashMap()

/**
 * 初始化玩家统计数据.
 */
fun initPlayerData(p: Player) {
    TODO("Location not serializable")
    val uuid = p.uniqueId
    joinTime[uuid] = System.currentTimeMillis()
    transaction(db) {
        SchemaUtils.createMissingTablesAndColumns(PlayerData)
        PlayerData.insertIgnore {
            it[PlayerData.uuid] = uuid
            it[name] = p.name
            it[first] = firstTime(p)
            it[total] = previousTotalTime(p)
        }
        if (!PlayerData.select { PlayerData.uuid eq uuid }.empty()) {
            val deathPoint = PlayerData.select { PlayerData.uuid eq uuid }.first()[PlayerData.death]
            if (deathPoint != null) {
                playerDeathPoints[uuid] = Json.decodeFromString(deathPoint)
            }
        }
    }
    playerHomes[uuid] = p.getHomes()
}

/**
 * 获取玩家首次进服时间.
 */
private fun firstTime(p: Player): String {
    return if (p.firstPlayed == 0L) {
        "yy.mm.dd hh:mm:ss".formatTime()
    } else {
        val c = Calendar.getInstance()
        c.timeInMillis = p.firstPlayed
        "yy.mm.dd hh:mm:ss".formatTime(c)
    }
}

/**
 * 获取玩家先前的入服时间.
 *
 * ! 特别注意这个 Statistic.PLAY_ONE_MINUTE, 它实际上返回的是 TICK, 后面可能会修复, 要小心
 */
private fun previousTotalTime(p: Player): Int = p.getStatistic(Statistic.PLAY_ONE_MINUTE) / 20 / 60