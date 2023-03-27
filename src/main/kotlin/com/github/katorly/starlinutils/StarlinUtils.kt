package com.github.katorly.starlinutils

import com.github.katorly.starlinutils.ConfigHandler.reloadConfig
import org.bukkit.Bukkit
import org.bukkit.Bukkit.getScheduler
import org.bukkit.NamespacedKey
import org.bukkit.event.HandlerList.unregisterAll
import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info
import taboolib.platform.BukkitPlugin

object StarlinUtils: Plugin() {
    val plugin by lazy { BukkitPlugin.getInstance() }
    var serverClosing = false
    val recipeKeys: MutableList<NamespacedKey> = ArrayList()

    override fun onEnable() {
        reloadConfig()
        RecipeHandler.registerConcreteRecipe()
        info("[StarlinUtils] 仓库: https://github.com/katorlys/StarlinUtils")
        info("[StarlinUtils] StarlinUtils 已加载! 为 星林宇宙 而做.")
    }

    override fun onDisable() {
        unregisterAll()
        getScheduler().cancelTasks(plugin)
        recipeKeys.forEach(Bukkit::removeRecipe)
        recipeKeys.clear()
        info("[StarlinUtils] StarlinUtils 已卸载!")
    }
}