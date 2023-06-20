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
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

/**
 * 处理和记录玩家统计数据.
 */
fun settlePlayerData(p: Player) {
    TODO("Location not serializable")
    val uuid = p.uniqueId
    p.saveHomes(playerHomes[uuid]!!)
    transaction(db) {
        SchemaUtils.createMissingTablesAndColumns(PlayerData)
        val previousTotal = PlayerData.select { PlayerData.uuid eq uuid }.first()[PlayerData.total]
        val onlineTime = onlineTime(p)
        PlayerData.update({ PlayerData.uuid eq uuid }) {
            it[last] = "yy.mm.dd hh:mm:ss".formatTime()
            it[total] = previousTotal + onlineTime
            joinTime.remove(uuid)
            if (playerDeathPoints.containsKey(uuid)) {
                it[death] = Json.encodeToString(playerDeathPoints[uuid])
            }
        }
        monthlyTime(p, onlineTime)
    }
}

/**
 * 结算玩家在线时长.
 */
private fun onlineTime(p: Player): Int {
    val uuid = p.uniqueId
    return if (joinTime.containsKey(uuid)) ((System.currentTimeMillis() - joinTime[uuid]!!) / 1000 / 60).toInt()
    else 0
}

/**
 * 结算玩家每月在线时长.
 *
 * @param t 当前在线时长
 */
private fun monthlyTime(p: Player, t: Int) {
    val previousMonthly = p.getMonthly()
    val yrmon = "yy.dd".formatTime()
    if (previousMonthly.isNotEmpty()) {
        if (previousMonthly.containsKey(yrmon)) {
            previousMonthly[yrmon] = previousMonthly[yrmon]!! + t
        } else previousMonthly[yrmon] = t
    } else previousMonthly[yrmon] = t
    p.saveMonthly(previousMonthly)
}