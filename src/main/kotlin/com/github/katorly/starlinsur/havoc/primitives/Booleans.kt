/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.havoc.primitives

import com.github.katorly.starlinsur.havoc.string.multicontain

/**
 * HavocBukkit
 * havocbukkit.utils.primitives.Booleans
 *
 * @author Katorly
 * @since 0.0.0
 */

/**
 * Safely convert Any to boolean.
 *
 * @param default default value to return if null, if not set: false
 * @return [Boolean] the boolean, true if 1, false if 0, default if null
 */
fun Any?.boolean(default: Boolean = false): Boolean {
    return when (val n = this) {
        is Boolean -> n

        is Double -> n.toInt().deter(default)
        is Long -> n.toInt().deter(default)
        is Float -> n.toInt().deter(default)
        is Int -> n.deter(default)
        is Short -> n.toInt().deter(default)
        is Byte -> n.toInt().deter(default)
        is String -> {
            if (n.multicontain("t", "r", "u")) true
            else if (n.multicontain("f", "a", "l", "s")) false else default
        }

        else -> default
    }
}

/**
 * 1 = true,
 * 0 = false.
 */
private fun Int.deter(default: Boolean): Boolean {
    return when (this) {
        1 -> true
        0 -> false
        else -> default
    }
}