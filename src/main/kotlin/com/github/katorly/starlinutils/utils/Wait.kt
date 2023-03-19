package com.github.katorly.starlinutils.utils

/**
 * Wait in Bukkit/Spigot
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

import com.github.katorly.starlinutils.StarlinUtils
import org.bukkit.scheduler.BukkitRunnable

class Wait {
    companion object {
        /**
         * Delay one second.
         *
         */
        fun onesec() {
            delay(1.0)
        }

        /**
         * Delay these second(s).
         *
         * @param seconds Seconds to delay.
         */
        fun delay(seconds: Double) {
            object : BukkitRunnable() {
                override fun run() {
                    cancel()
                }
            }.runTaskLater(StarlinUtils.INSTANCE, seconds.toLong() * 20)
        }
    }
}