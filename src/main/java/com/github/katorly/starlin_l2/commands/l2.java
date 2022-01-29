package com.github.katorly.starlin_l2.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.katorly.starlin_l2.starlin_l2;
import com.github.katorly.starlin_l2.backup.Messager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class l2 implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration timedata = starlin_l2.timedata.getConfig();
        if (command.getName().equalsIgnoreCase("l2")) { //Main command of the plugin.
            if (args.length < 1) {
                Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7用法: /l2 <参数>. 可用参数: time");
            } else if (Objects.equals(args[0], "time")) { //Get player's play time information.
                if (args.length == 1) {
                    if (!(sender instanceof Player)) {
                        Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7用法: /l2 time <玩家ID>.");
                    } else {
                        Player player = (Player) sender;
                        String u = player.getUniqueId().toString();
                        String pname = player.getName().toString();
                        String first_time;
                        if (starlin_l2.timedata.getConfig().contains(u + ".first-time")) first_time = timedata.getString(u + ".first-time");
                        else first_time = "未知";
                        String total_time;
                        if (starlin_l2.timedata.getConfig().contains(u + ".total")) total_time = String.format("%.1f", timedata.getDouble(u + ".total"));
                        else total_time = "未知";
                        Messager.sendMessage(player, "&b&l星林宇宙 &r&8>> &7您 (&f" + pname + "&7) 的游玩信息:");
                        Messager.sendMessage(player, " &7首次加入: &f" + first_time);
                        Messager.sendMessage(player, " &7总计时长: &f" + total_time + " 小时");
                        Messager.sendMessage(player, " &7游玩信息在您退出重进后会更新.");
                    }
                } else if (args.length == 2) {
                    Player p = null;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (Objects.equals(args[1], player.getName())) {
                            p = player;
                        }
                    }
                    if (p != null) {
                        String u = p.getUniqueId().toString();
                        String pname = p.getName().toString();
                        String first_time;
                        if (starlin_l2.timedata.getConfig().contains(u + ".first-time")) first_time = timedata.getString(u + ".first-time");
                        else first_time = "未知";
                        String total_time;
                        if (starlin_l2.timedata.getConfig().contains(u + ".total")) total_time = String.format("%.1f", timedata.getDouble(u + ".total"));
                        else total_time = "未知";
                        Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7玩家 &f" + pname + "&7 的游玩信息:");
                        Messager.senderMessage(sender, " &7首次加入: &f" + first_time);
                        Messager.senderMessage(sender, " &7总计时长: &f" + total_time + " 小时");
                        Messager.senderMessage(sender, " &7游玩信息在该玩家退出重进后会更新.");
                    } else {
                        Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7玩家不在线或不存在!");
                    }
                } else {
                    Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7用法: /l2 time [玩家ID].");
                }
            } else {
                Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7用法: /l2 <参数>. 可用参数: time");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        }
        if (args.length == 1) {
            List<String> sub = new ArrayList<>();
            sub.add("time");
            return sub;
        }
        if (args.length == 2) {
            if (Objects.equals(args[0], "time")) {
                List<String> sub = new ArrayList<>();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    sub.add(player.getName());
                }
                return sub;
            }
        }
        return null;
    }
}
