/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.havoc.math

/**
 * HavocBukkit
 * havocbukkit.utils.math.Compare
 *
 * @author Katorly
 * @since 0.0.0
 */

/**
 * Compare size of two numbers.
 * Reorder nothing if they are the same.
 *
 * @return [Pair] first is smaller, second is larger
 */
fun compareSize(num1: Double, num2: Double): Pair<Double, Double> {
    return if (num1 <= num2) Pair(num1, num2)
    else Pair(num2, num1)
}
fun compareSize(num1: Long, num2: Long): Pair<Long, Long> {
    return if (num1 <= num2) Pair(num1, num2)
    else Pair(num2, num1)
}
fun compareSize(num1: Float, num2: Float): Pair<Float, Float> {
    return if (num1 <= num2) Pair(num1, num2)
    else Pair(num2, num1)
}
fun compareSize(num1: Int, num2: Int): Pair<Int, Int> {
    return if (num1 <= num2) Pair(num1, num2)
    else Pair(num2, num1)
}
fun compareSize(num1: Short, num2: Short): Pair<Short, Short> {
    return if (num1 <= num2) Pair(num1, num2)
    else Pair(num2, num1)
}
fun compareSize(num1: Byte, num2: Byte): Pair<Byte, Byte> {
    return if (num1 <= num2) Pair(num1, num2)
    else Pair(num2, num1)
}

/**
 * Compare length of two strings.
 * The first string will be in the first place if their lengths are the same.
 *
 * @return [Pair] first length is smaller, second length is larger
 */
fun compareLength(s1: String, s2: String): Pair<String, String> {
    return if (s1.length <= s2.length) Pair(s1, s2)
    else Pair(s2, s1)
}