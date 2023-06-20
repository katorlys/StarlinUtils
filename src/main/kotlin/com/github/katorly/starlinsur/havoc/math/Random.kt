/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.havoc.math

import java.security.SecureRandom
import java.util.concurrent.ThreadLocalRandom
import kotlin.random.Random
import kotlin.random.asKotlinRandom

/**
 * HavocBukkit
 * havocbukkit.utils.math.Random
 *
 * Use this to generate more random numbers.
 * For strings, please use @link work.katorly.havocbukkit.utils.string.Random.
 *
 * @author Katorly
 * @since 0.0.0
 */

/**
 * Generate ThreadLocalRandom.
 * * faster than SecureRandom
 *
 * @return [Random]
 */
fun threadRandom(): Random = ThreadLocalRandom.current().asKotlinRandom()

/**
 * Generate SecureRandom.
 * * safer than ThreadLocalRandom
 *
 * @return [Random]
 */
fun secureRandom(): Random = SecureRandom().asKotlinRandom()

fun random(num1: Double, num2: Double = 0.0): Double {
    val pair = compareSize(num1, num2)
    return SecureRandom().nextDouble(pair.first, pair.second + Double.MIN_VALUE)
}
fun random(num1: Long, num2: Long = 0): Long {
    val pair = compareSize(num1, num2)
    return SecureRandom().nextLong(pair.first, pair.second + Long.MIN_VALUE)
}
fun random(num1: Float, num2: Float = 0F): Float {
    val pair = compareSize(num1, num2)
    return SecureRandom().nextDouble(pair.first.toDouble(), pair.second.toDouble() + Float.MIN_VALUE).toFloat()
}
fun random(num1: Int, num2: Int = 0): Int {
    val pair = compareSize(num1, num2)
    return SecureRandom().nextInt(pair.first, pair.second + 1)
}
fun random(num1: Short, num2: Short = 0): Short {
    val pair = compareSize(num1, num2)
    return SecureRandom().nextInt(pair.first.toInt(), pair.second.toInt() + 1).toShort()
}
fun random(num1: Byte, num2: Byte = 0): Byte {
    val pair = compareSize(num1, num2)
    return SecureRandom().nextInt(pair.first.toInt(), pair.second.toInt() + 1).toByte()
}