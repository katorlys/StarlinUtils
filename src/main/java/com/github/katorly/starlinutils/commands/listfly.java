package com.github.katorly.starlinutils.commands;

import com.github.katorly.starlinutils.backup.Messager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class listfly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("listfly")) { //Lists the players that have fly.
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    p.performCommand("lp group fly listmembers");
                } else {
                    Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), "lp group fly listmembers");
                }
            } else {
                Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7用法: /listfly.");
            }
        }
        return true;
    }
}
