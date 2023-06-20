/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.havoc

import com.github.katorly.starlinsur.havoc.Logger.debug
import org.bukkit.Bukkit

/**
 * HavocBukkit
 * havocbukkit.core.Logger
 *
 * @author Katorly
 * @since 0.0.0
 */

/**
 * Log an INFO message.
 *
 * @param message
 */
fun info(message: String) {
    Bukkit.getLogger().info(prefix + message)
}

/**
 * Log a WARNING message.
 *
 * @param message
 */
fun warn(message: String) {
    Bukkit.getLogger().warning(prefix + message)
}

/**
 * Log a SEVERE message.
 *
 * @param message
 */
fun error(message: String) {
    Bukkit.getLogger().severe(prefix + message)
}

/**
 * Log a DEBUG message.
 * Only logs if debug mode is enabled.
 *
 * @param message
 */
fun Debug(message: String) {
    if (debug) info(prefix + message)
}

object Logger {
    /* Debug Mode */
    var debug = false

    /**
     * Toggle Debug Mode on or off.
     */
    fun debugMode() {
        debug = !debug
    }

    /**
     * Set Debug Mode on or off.
     */
    fun debugMode(boolean: Boolean) {
        debug = boolean
    }
}