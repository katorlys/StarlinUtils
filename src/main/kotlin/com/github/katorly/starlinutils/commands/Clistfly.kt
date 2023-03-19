package com.github.katorly.starlinutils.commands

import com.github.katorly.starlinutils.ConfigHandler
import com.github.katorly.starlinutils.utils.Messager
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player

/**
 * 列出所有拥有飞行权限的玩家.
 *
 */
class Clistfly : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (command.name.equals("listfly", ignoreCase = true)) {
            if (args.isEmpty()) {
                val config: FileConfiguration? = ConfigHandler.config.getConfig()
                val cmd: String? = config?.getString("fly.list-fly")
                if (sender is Player) {
                    sender.performCommand(cmd!!)
                } else {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().consoleSender, cmd!!)
                }
            } else {
                Messager.srm(sender, "&b&l星林宇宙 &r&8>> &7用法: /listfly.")
            }
        }
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<String>
    ): List<String>? {
        return null
    }
}