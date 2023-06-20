/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.havoc

import com.github.katorly.starlinsur.StarlinSMP.Companion.plugin
import org.bukkit.scheduler.BukkitTask

/**
 * HavocBukkit
 * havocbukkit.core.Delay
 *
 * @author Katorly
 * @since 0.0.0
 */

/**
 * Bukkit Runnable execute after delay.
 *
 * @param delay delay in seconds
 * @param async whether to use async runnable
 * @param runnable your runnable
 * @throws IllegalArgumentException if delay is less than 0
 * @return [BukkitTask]
 */
fun delay(delay: Number = 0, async: Boolean = false, runnable: Runnable): BukkitTask {
    check(delay.toInt() >= 0) { "Delay must be greater than 0" }
    return if (async) {
        plugin.server.scheduler.runTaskLaterAsynchronously(plugin, runnable, (delay.toDouble() * 20).toLong())
    } else {
        plugin.server.scheduler.runTaskLater(plugin, runnable, (delay.toDouble() * 20).toLong())
    }
}

/**
 * Bukkit Runnable execute after delay, and then run again after period.
 *
 * @param delay delay in seconds
 * @param period period in ticks
 * @param async whether to use async runnable
 * @param runnable your runnable
 * @throws IllegalArgumentException if delay or period is less than 0
 * @return [BukkitTask]
 */
fun period(delay: Number = 0, period: Number = 0, async: Boolean = false, runnable: Runnable): BukkitTask {
    check(delay.toInt() >= 0) { "Delay must be greater than 0" }
    check(period.toInt() >= 0) { "Period must be greater than 0" }
    return if (async) {
        plugin.server.scheduler.runTaskTimerAsynchronously(plugin, runnable, (delay.toDouble() * 20).toLong(), (period.toDouble() * 20).toLong())
    } else {
        plugin.server.scheduler.runTaskTimer(plugin, runnable, (delay.toDouble() * 20).toLong(), (period.toDouble() * 20).toLong())
    }
}

/**
 * Delay one second and do nothing.
 */
fun onesec() {
    delay(1){
        // do nothing
    }
}