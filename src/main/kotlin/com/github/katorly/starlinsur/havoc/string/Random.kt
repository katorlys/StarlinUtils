/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.havoc.string

import java.security.SecureRandom

/**
 * HavocBukkit
 * havocbukkit.utils.string.Random
 *
 * @author Katorly
 * @since 0.0.0
 */

fun randomString(length: Int): String {
    val chars = ('a'..'z') + ('A'..'Z') + ('0'..'9')
    return (1..length).map { chars[SecureRandom().nextInt(chars.size)] }.joinToString("")
}