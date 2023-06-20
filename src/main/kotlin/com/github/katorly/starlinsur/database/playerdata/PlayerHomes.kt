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
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.bukkit.Location
import org.bukkit.entity.Player
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

/**
 * 从数据库获取玩家的家.
 */
fun Player.getHomes(): MutableMap<String, Location> = transaction(db) {
    TODO("Location not serializable")
    SchemaUtils.createMissingTablesAndColumns(PlayerData)
    val string = PlayerData.select { PlayerData.uuid eq this@getHomes.uniqueId }.first()[PlayerData.homes]
    return@transaction if (string != null) {
        Json.decodeFromString<MutableMap<String, Location>>(string)
    } else {
        HashMap()
    }
}

/**
 * 储存玩家的家到数据库中.
 */
fun Player.saveHomes(data: MutableMap<String, Location>) {
    TODO("Location not serializable")
    transaction(db) {
        PlayerData.update({ PlayerData.uuid eq this@saveHomes.uniqueId }) {
            it[homes] = Json.encodeToString(data)
        }
    }
}