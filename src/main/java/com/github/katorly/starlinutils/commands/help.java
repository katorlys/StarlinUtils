package com.github.katorly.starlinutils.commands;

import com.github.katorly.starlinutils.StarlinUtils;
import com.github.katorly.starlinutils.backup.Messager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

public class help implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = StarlinUtils.config.getConfig();
        if (command.getName().equalsIgnoreCase("help")) { //Displays the help document when player excutes "/help".
            Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7新手指南: &f" + config.getString("help-document"));
        }
        return true;
    }
}
