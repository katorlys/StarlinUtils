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
 * havocbukkit.utils.string.Empty
 *
 * @author Katorly
 * @since 0.0.0
 */

/**
 * Remove all whitespace in a String.
 * This is way faster than filter, filterNot and replace.
 *
 * @return [String] the String without whitespace
 */
fun String.removeSpace(): String {
    val s = StringBuilder(this.length)
    for (c in this) if (!c.isWhitespace()) s.append(c)
    return s.toString()
}

/**
 * Check if a String is empty or null.
 * (Returns true if String is empty or null)
 *
 * @return [Boolean] true if String is empty.
 */
@JvmName("isitBlank")
fun String?.isBlank(): Boolean {
    return (this?.removeSpace() ?: "") == ""
}
@JvmName("isBlank")
fun String.isBlank(): Boolean {
    return this.removeSpace() == ""
}

/**
 * Check if a String isn't empty or null.
 * (Returns true if String isn't empty or null)
 *
 * @return [Boolean] true if String isn't empty.
 */
@JvmName("isitNotBlank")
fun String?.isNotBlank(): Boolean {
    return !this.isBlank()
}
@JvmName("isNotBlank")
fun String.isNotBlank(): Boolean {
    return !this.isBlank()
}