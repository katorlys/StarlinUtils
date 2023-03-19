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
 * 给予一名玩家飞行权限.
 *
 */
class Cgivefly : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (command.name.equals("givefly", ignoreCase = true)) {
            if (args.size == 1) {
                val config: FileConfiguration? = ConfigHandler.config.getConfig()
                val cmd: String? = config?.getString("fly.give-fly")?.replace("<player>", args[0])
                if (sender is Player) { // 以玩家身份执行, 因此无需检查是否有权限.
                    sender.performCommand(cmd!!)
                } else {
                    Bukkit.getServer()
                        .dispatchCommand(Bukkit.getServer().consoleSender, cmd!!)
                }
            } else {
                Messager.srm(sender, "&b&l星林宇宙 &r&8>> &7用法: /givefly <玩家ID>.")
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