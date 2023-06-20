/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.havoc.time

/**
 * HavocBukkit
 * havocbukkit.utils.time.TimeConvert
 *
 * @author Katorly
 * @since 0.0.0
 */

/**
 * Convert milliseconds to seconds.
 *
 * @return [Double]
 */
fun Long.milli2sec(): Double {
    return (this / 1000).toDouble()
}