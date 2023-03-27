package com.github.katorly.starlinutils

import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration

object ConfigHandler {
    @Config("config.yml")
    lateinit var config: Configuration

    var conf: MutableMap<String, String> = HashMap()
    var confl: MutableMap<String, List<String>> = HashMap()
    lateinit var prefix: String

//    @Config("gamerule.yml")
//    lateinit var gm: Configuration

    fun reloadConfig() {
        config.reload()
        cache()
    }

    private fun cache() {
        conf.clear()
        confl.clear()
        for (key in config.getKeys(true)) {
            if (config.isString(key) && config.getString(key) != null) conf.put(key, config.getString(key)!!)
            else if (config.getStringList(key).isNotEmpty()) confl.put(key, config.getStringList(key))
        }
        if (conf["prefix"] != null) prefix = conf["prefix"]!!
        else prefix = "&b&l星林宇宙 &r&7>> &7"
    }
}