package com.github.katorly.starlin_l2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.katorly.starlin_l2.backup.messageSender;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandHandler implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration timedata = starlin_l2.timedata.getConfig();
        if (command.getName().equalsIgnoreCase("l2")) {
            if (args.length < 1) {
                sender.sendMessage(messageSender.Color("&b&l星林宇宙 &r&8>> &7用法: /l2 <参数>. 可用参数: time"));
            } else if (Objects.equals(args[0], "time")) { //Get player's play time information.
                if (args.length == 1) {
                    if (!(sender instanceof Player)) {
                        sender.sendMessage(messageSender.Color("&b&l星林宇宙 &r&8>> &7用法: /l2 time <玩家ID>."));
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
                        messageSender.sendMessage(player, "&b&l星林宇宙 &r&8>> &7您 (&f" + pname + "&7) 的游玩信息:");
                        messageSender.sendMessage(player, " &7首次加入: &f" + first_time);
                        messageSender.sendMessage(player, " &7总计时长: &f" + total_time + " 小时");
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
                        sender.sendMessage(messageSender.Color("&b&l星林宇宙 &r&8>> &7玩家 &f" + pname + "&7 的游玩信息:"));
                        sender.sendMessage(messageSender.Color(" &7首次加入: &f" + first_time));
                        sender.sendMessage(messageSender.Color(" &7总计时长: &f" + total_time + " 小时"));
                    } else {
                        sender.sendMessage(messageSender.Color("&b&l星林宇宙 &r&8>> &7玩家不在线或不存在!"));
                    }
                } else {
                    sender.sendMessage(messageSender.Color("&b&l星林宇宙 &r&8>> &7用法: /l2 time [玩家ID]."));
                }
            } else {
                sender.sendMessage(messageSender.Color("&b&l星林宇宙 &r&8>> &7用法: /l2 <参数>. 可用参数: time"));
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command commandd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return null;
        }
        if (args.length == 1) {
            List<String> sub = new ArrayList<>();
            sub.add("time");
            return sub;
        }
        if (args.length == 2) {
            if (args[0] == "time") {
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
