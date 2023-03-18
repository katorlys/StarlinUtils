package com.github.katorly.starlinutils

import com.github.katorly.starlinutils.utils.Configer

class ConfigHandler {
    companion object {
        lateinit var config: Configer

        /**
         * Register all the configs of the plugin.
         *
         */
        fun registerConfigs() {
            config = Configer(StarlinUtils.INSTANCE, "", "config.yml")
            config.saveDefaultConfig()
        }

        /**
         * Reload all the config.
         *
         */
        fun reloadConfig() {
            config.reloadConfig()
        }
    }
}