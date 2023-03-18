package com.github.katorly.starlinutils

import com.github.katorly.starlinutils.events.*
import org.bukkit.Bukkit

class EventHandler {
    companion object {
        /**
         * Register all the events of the plugin.
         *
         */
        fun registerEvents() {
            val events = arrayOf(
                CropTrample(),
                PlayerCommand(),
                PlayerJoin()
            )
            for (name in events) {
                Bukkit.getPluginManager().registerEvents(name, StarlinUtils.INSTANCE)
            }
        }
    }
}