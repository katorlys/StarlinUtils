/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.utils

/**
 * Wait in Bukkit/Spigot
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

import taboolib.common.platform.function.submit

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