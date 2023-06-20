/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.database.playerdata

import org.jetbrains.exposed.sql.Table

object PlayerData : Table() {
    val uuid = uuid("UUID").uniqueIndex()
    val name = varchar("name", 16)
    var first = varchar("first_join", 19)
    var last = varchar("last_quit", 19).nullable()
    var total = integer("total_time")
    var monthly = text("monthly_time").nullable()
    var homes = text("homes").nullable()
    var death = text("death_point").nullable()

    override val primaryKey = PrimaryKey(uuid)
}