/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.utils

object Strings {
    /**
     * Replace multiple values in a String.
     * Used StringBuilder instead of using replace() in a loop.
     *
     * @author Katorly
     * @param re values to replace
     * @return [String] the final String
     */
    fun String.multireplace(vararg re: Pair<String, Any>): String {
        val s = StringBuilder(this)
        for ((o, new) in re) {
            val n = new.toString()
            var start = s.indexOf(o)
            while (start != -1) {
                val end = start + o.length
                s.replace(start, end, n)
                start = s.indexOf(o, start + n.length)
            }
        }
        return s.toString()
    }

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
}