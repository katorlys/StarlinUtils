/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.havoc.string

/**
 * HavocBukkit
 * havocbukkit.utils.string.Contain
 *
 * @author Katorly
 * @since 0.0.0
 */

/**
 * Check whether a String contains
 * one of the given values.
 *
 * @param o values to check
 * @return [Boolean] whether it contains one of the given values
 */
fun String.multicontain(vararg o: String): Boolean {
    return o.all { this.contains(it) }
}