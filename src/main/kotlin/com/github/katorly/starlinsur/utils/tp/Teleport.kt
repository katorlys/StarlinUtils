/*
 * Copyright (c) 2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinsur.utils.tp

import com.github.katorly.starlinsur.havoc.delay
import org.bukkit.Location
import org.bukkit.entity.Player

/**
 * You can't teleport players asynchronously without Folia.
 */

/**
 * Teleport players synchronously.
 *
 * @param location The location to teleport to
 * @param delay The delay in seconds before teleporting
 */
fun Player.teleportSync(location: Location, delay: Number = 0) {
    delay(delay) { this.teleportAfterLoad(location) }
}

/**
 * Teleport players after loading the target chunk.
 * This will not load the chunk if it's already loaded.
 *
 * @param location The location to teleport to
 */
fun Player.teleportAfterLoad(location: Location) {
    val c = location.chunk
    if (c.isLoaded) this.teleport(location)
    else c.load(); this.teleport(location)
}