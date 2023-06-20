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
 * havocbukkit.utils.primitives.Doubles
 *
 * @author Katorly
 * @since 0.0.0
 */

/**
 * Safely convert Any to Double.
 *
 * @param default default value to return if null, if not set: 0.0
 * @return [Double] 1.0 if true, 0.0 if false, default if null
 */
fun Any?.double(default: Double = 0.0): Double {
    return when (val n = this) {
        is Double -> n

        is Long -> n.toDouble()
        is Float -> n.toDouble()
        is Int -> n.toDouble()
        is Short -> n.toDouble()
        is Byte -> n.toDouble()
        is Boolean -> if (n) 1.0 else 0.0
        is String -> try {
            n.toDoubleOrNull() ?: default
        } catch (e: NumberFormatException) {
            default
        }

        else -> default
    }
}