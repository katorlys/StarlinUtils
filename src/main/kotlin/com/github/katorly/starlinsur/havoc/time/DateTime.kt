/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.havoc.time

import com.github.katorly.starlinsur.havoc.string.multireplace
import java.util.*

/**
 * HavocBukkit
 * havocbukkit.utils.time.DateTime
 *
 * Quicker access to Java Calendar.
 *
 * @author Katorly
 * @since 0.0.0
 */

/**
 * Get Calendar instance.
 *
 */
fun getTime(zone: String): Calendar = Calendar.getInstance(TimeZone.getTimeZone(zone))
fun getTime(zone: TimeZone = TimeZone.getDefault()): Calendar = Calendar.getInstance(zone)

fun Calendar.tz(): TimeZone = this.timeZone()
fun Calendar.timeZone(): TimeZone = this.timeZone

fun Calendar.era() = this.get(Calendar.ERA)

fun Calendar.yr() = this.year()
fun Calendar.year() = this.get(Calendar.YEAR)

fun Calendar.mon() = this.month()
fun Calendar.month() = this.get(Calendar.MONTH)

fun Calendar.woy() = this.weekOfYear()
fun Calendar.weekOfYear() = this.get(Calendar.WEEK_OF_YEAR)

fun Calendar.wom() = this.weekOfMonth()
fun Calendar.weekOfMonth() = this.get(Calendar.WEEK_OF_MONTH)

// The same as DAY_OF_MONTH
fun Calendar.d() = this.day()
fun Calendar.day() = this.get(Calendar.DATE)

fun Calendar.doy() = this.dayOfYear()
fun Calendar.dayOfYear() = this.get(Calendar.DAY_OF_YEAR)

fun Calendar.dow() = this.dayOfWeek()
fun Calendar.dayOfWeek() = this.get(Calendar.DAY_OF_WEEK)

fun Calendar.hr() = this.hour()
fun Calendar.hour() = this.get(Calendar.HOUR_OF_DAY)

fun Calendar.min() = this.minute()
fun Calendar.minute() = this.get(Calendar.MINUTE)

fun Calendar.sec() = this.second()
fun Calendar.second() = this.get(Calendar.SECOND)

fun Calendar.milli() = this.millisecond()
fun Calendar.millisecond() = this.get(Calendar.MILLISECOND)

/**
 * Apply time arguments to a string.
 * e.g. `{day}.{month}.{year} {hour}:{minute}:{second}`
 *
 * @param c Calendar instance. Defaults to current time.
 */
fun String.applyTime(c: Calendar = getTime()): String {
    return this.multireplace(
        "{day}" to c.d(),
        "{month}" to c.mon(),
        "{year}" to c.yr(),
        "{era}" to c.era(),
        "{hour}" to c.hr(),
        "{minute}" to c.min(),
        "{second}" to c.sec(),
        "{milli}" to c.milli(),

        "{weekOfYear}" to c.woy(),
        "{weekOfMonth}" to c.wom(),
        "{dayOfYear}" to c.doy(),
        "{dayOfWeek}" to c.dow()
    )
}

/**
 * Format time arguments in a string.
 * e.g. `yy.mm.dd hh:mm:ss`
 *
 * @param c Calendar instance. Defaults to current time.
 */
fun String.formatTime(c: Calendar = getTime()): String {
    return this.multireplace(
        "dd" to c.d(),
        "mm" to c.mon(),
        "yy" to c.yr(),
        "ee" to c.era(),
        "hh" to c.hr(),
        "mm" to c.min(),
        "ss" to c.sec(),
        "ms" to c.milli(),

        "woy" to c.woy(),
        "wom" to c.wom(),
        "doy" to c.doy(),
        "dow" to c.dow()
    )
}