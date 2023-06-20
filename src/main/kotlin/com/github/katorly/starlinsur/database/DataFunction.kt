/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.database

import com.github.katorly.starlinsur.StarlinSMP.Companion.plugin
import com.github.katorly.starlinsur.config.conf
import com.github.katorly.starlinsur.database.playerdata.PlayerData
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction

val db by lazy { Database.connect("jdbc:sqlite:${plugin.dataFolder}/${conf.get("database.name")}.db") }

fun initDB() {
    transaction(db) {
        SchemaUtils.createMissingTablesAndColumns(PlayerData)
    }
}

fun closeDB() {
    TransactionManager.closeAndUnregister(db)
}