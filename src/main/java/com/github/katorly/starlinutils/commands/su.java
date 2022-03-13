package com.github.katorly.starlinutils.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.github.katorly.starlinutils.StarlinUtils;
import com.github.katorly.starlinutils.backup.Messager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class su implements TabExecutor {
    public List<UUID> suCooldown = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration timedata = StarlinUtils.timedata.getConfig();
        if (command.getName().equalsIgnoreCase("su")) { //Main command of the plugin.
            if (args.length < 1) {
                Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7用法: /su <参数>. 可用参数: time");
            } else if (Objects.equals(args[0], "time")) { //Get player's play time information.
                if (args.length == 1) {
                    if (!(sender instanceof Player)) {
                        Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7用法: /su time <玩家ID>.");
                    } else {
                        Player player = (Player) sender;
                        String u = player.getUniqueId().toString();
                        String pname = player.getName().toString();
                        String first_time;
                        if (StarlinUtils.timedata.getConfig().contains(u + ".first-time")) first_time = timedata.getString(u + ".first-time");
                        else first_time = "未知";
                        String total_time;
                        if (StarlinUtils.timedata.getConfig().contains(u + ".total")) total_time = String.format("%.1f", timedata.getDouble(u + ".total"));
                        else total_time = "未知";
                        Messager.sendMessage(player, "&b&l星林宇宙 &r&8>> &7您 (&f" + pname + "&7) 的在线时长:");
                        Messager.sendMessage(player, " &7首次加入: &f" + first_time);
                        Messager.sendMessage(player, " &7总计时长: &f" + total_time + " 小时");
                        Messager.sendMessage(player, " &7在线时长在您退出重进后会更新.");
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
                        if (StarlinUtils.timedata.getConfig().contains(u + ".first-time")) first_time = timedata.getString(u + ".first-time");
                        else first_time = "未知";
                        String total_time;
                        if (StarlinUtils.timedata.getConfig().contains(u + ".total")) total_time = String.format("%.1f", timedata.getDouble(u + ".total"));
                        else total_time = "未知";
                        Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7玩家 &f" + pname + "&7 的在线时长:");
                        Messager.senderMessage(sender, " &7首次加入: &f" + first_time);
                        Messager.senderMessage(sender, " &7总计时长: &f" + total_time + " 小时");
                        Messager.senderMessage(sender, " &7在线时长在该玩家退出重进后会更新.");
                    } else {
                        Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7玩家不在线或不存在!");
                    }
                } else {
                    Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7用法: /su time [玩家ID].");
                }
            } else if (Objects.equals(args[0], "info")) { //Displays player's in-game information
                if (!(sender instanceof Player)) {
                    Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7只有玩家才能查看自己的游戏状态!");
                } else {
                    final Player p= (Player) sender;
                    if (!suCooldown.contains(p.getUniqueId())) {
                        Messager.senderMessage(p, "&b&l星林宇宙 &r&8>> &7您当前的游戏状态:");
                        Messager.senderMessage(p, " &7游戏ID: &f" + p.getName());
                        Messager.senderMessage(p, " &7UUID: &f" + p.getUniqueId());
                        Messager.senderMessage(p, " &7生命值: &f" + p.getHealth());
                        Messager.senderMessage(p, " &7饱食度: &f" + p.getFoodLevel());
                        Messager.senderMessage(p, " &7经验等级: &f" + p.getLevel());
                        Messager.senderMessage(p, " &7游戏模式: &f" + p.getGameMode());
                        Messager.senderMessage(p, " &7面朝: &f" + p.getFacing());
                        Messager.senderMessage(p, " &7当前位置: &f" + p.getWorld().getName() + ", " + String.format("%.2f", p.getLocation().getX()) + ", " + String.format("%.2f", p.getLocation().getY()) + ", " + String.format("%.2f", p.getLocation().getZ()));
                        Messager.senderMessage(p, " &7延迟: &f" + p.getPing() + "ms");
                        suCooldown.add(p.getUniqueId());
                        FileConfiguration config = StarlinUtils.config.getConfig();
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                suCooldown.remove(p.getUniqueId());
                            }
                        }.runTaskLater(StarlinUtils.INSTANCE, config.getInt("su-command-cooldown") * 20);
                    } else { //cooldown
                        Messager.senderMessage(p, "&b&l星林宇宙 &r&8>> &7您操作太频繁了!");
                    }
                }
            } else {
                Messager.senderMessage(sender, "&b&l星林宇宙 &r&8>> &7用法: /su <参数>. 可用参数: time");
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
            sub.add("info");
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
