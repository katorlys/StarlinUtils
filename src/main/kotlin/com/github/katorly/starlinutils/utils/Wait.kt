package com.github.katorly.starlinutils.utils

import taboolib.common.platform.function.submit

/**
 * Wait in Bukkit/Spigot
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

object Wait {
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
        submit(delay = seconds.toLong()) {
            cancel()
        }
    }
}