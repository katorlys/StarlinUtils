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
 * 移除一名玩家的飞行权限.
 *
 */
class Cdelfly : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (command.name.equals("delfly", ignoreCase = true)) {
            if (args.size == 1) {
                val config: FileConfiguration? = ConfigHandler.config.getConfig()
                val cmd: String? = config?.getString("fly.del-fly")?.replace("<player>", args[0])
                if (sender is Player) {
                    sender.performCommand(cmd!!)
                } else {
                    Bukkit.getServer()
                        .dispatchCommand(Bukkit.getServer().consoleSender, cmd!!)
                }
            } else {
                Messager.srm(sender, "&b&l星林宇宙 &r&8>> &7用法: /delfly <玩家ID>.")
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
        if (sender !is Player) {
            return null
        } else if (sender.isOp && args.size == 1) {
            if (args[0] == "givefly") {
                val sub: MutableList<String> = ArrayList()
                for (player in Bukkit.getOnlinePlayers()) {
                    sub.add(player.name)
                }
                return sub
            }
        }
        return null
    }
}