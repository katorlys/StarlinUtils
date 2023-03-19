package com.github.katorly.starlinutils

import org.bukkit.Bukkit
import org.bukkit.Bukkit.getScheduler
import org.bukkit.NamespacedKey
import org.bukkit.event.HandlerList
import org.bukkit.plugin.java.JavaPlugin

class StarlinUtils: JavaPlugin() {
    companion object {
        lateinit var INSTANCE: StarlinUtils
            private set
        var serverClosing = false
        val recipeKeys: MutableList<NamespacedKey> = ArrayList()
    }

    override fun onEnable() {
        INSTANCE = this // NEVER to be null!
        EventHandler.registerEvents()
        CommandHandler.registerCommands()
        ConfigHandler.registerConfigs()
        RecipeHandler.registerConcreteRecipe()
        logger.info("[StarlinUtils] 仓库: https://github.com/katorlys/StarlinUtils")
        logger.info("[StarlinUtils] StarlinUtils 已加载! 为 星林宇宙 而做.")
    }

    override fun onDisable() {
        HandlerList.unregisterAll(this)
        ConfigHandler.reloadConfig()
        getScheduler().cancelTasks(this)
        recipeKeys.forEach(Bukkit::removeRecipe)
        recipeKeys.clear()
        logger.info("[StarlinUtils] StarlinUtils 已卸载!")
    }
}