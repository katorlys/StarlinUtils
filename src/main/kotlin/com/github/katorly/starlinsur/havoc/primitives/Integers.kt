/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.havoc.primitives

/**
 * HavocBukkit
 * havocbukkit.utils.primitives.Integers
 *
 * @author Katorly
 * @since 0.0.0
 */

/**
 * Safely convert Any to Integer.
 *
 * @param default default value to return if null, if not set: 0
 * @return [Int] 1 if true, 0 if false, default if null
 */
fun Any?.int(default: Int = 0): Int = this.integer(default)
fun Any?.integer(default: Int = 0): Int {
    return when (val n = this) {
        is Int -> n

        is Double -> n.toInt()
        is Long -> n.toInt()
        is Float -> n.toInt()
        is Short -> n.toInt()
        is Byte -> n.toInt()
        is Boolean -> if (n) 1 else 0
        is String -> try {
            n.toIntOrNull() ?: default
        } catch (ignore: NumberFormatException) {
            default
        }

        else -> default
    }
}