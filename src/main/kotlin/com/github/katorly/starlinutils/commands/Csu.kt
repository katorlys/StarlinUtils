package com.github.katorly.starlinutils.commands

import com.github.katorly.starlinutils.ConfigHandler
import com.github.katorly.starlinutils.utils.Messager
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.entity.Player

/**
 * 插件主命令.
 *
 */
class Csu : TabExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (command.name.equals("su", ignoreCase = true)) {
            if (args.size == 1) {
                if (!sender.isOp) {
                    Messager.srm(sender, "&b&l星林宇宙 &r&8>> &7没有权限!")
                } else if (args[0] == "reload") {
                    ConfigHandler.reloadConfig()
                    Messager.srm(sender, "&b&l星林宇宙 &r&8>> &7成功重载插件配置.")
                }
            } else {
                Messager.srm(sender, "&b&l星林宇宙 &r&8>> &7用法: /su <参数>.")
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
        } else if (sender.isOp) {
            val sub: MutableList<String> = ArrayList()
            sub.add("reload")
            return sub
        }
        return null
    }
}