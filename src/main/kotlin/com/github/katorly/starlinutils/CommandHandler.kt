package com.github.katorly.starlinutils

import com.github.katorly.starlinutils.commands.*
import org.bukkit.Bukkit
import org.bukkit.command.CommandExecutor
import org.bukkit.command.TabCompleter

class CommandHandler {
    companion object {
        /**
         * Register all the commands of the plugin.
         * Please make sure every argument is corresponding.
         *
         */
        fun registerCommands() {
            val commands = arrayOf(
                "delfly",
                "givefly",
                "listfly",
                "su"
            )
            val excutors = arrayOf<CommandExecutor>(
                Cdelfly(),
                Cgivefly(),
                Clistfly(),
                Csu()
            )
            val completers = arrayOf<TabCompleter>(
                Cdelfly(),
                Cgivefly(),
                Clistfly(),
                Csu()
            )
            for (i in commands.indices) {
                Bukkit.getPluginCommand(commands[i])!!.setExecutor(excutors[i])
                Bukkit.getPluginCommand(commands[i])!!.tabCompleter = completers[i]
            }
        }
    }
}