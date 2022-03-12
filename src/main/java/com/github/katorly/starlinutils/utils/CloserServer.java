package com.github.katorly.starlinutils.utils;

import com.github.katorly.starlinutils.StarlinUtils;
import com.github.katorly.starlinutils.backup.Messager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.scheduler.BukkitRunnable;

public class CloserServer {
    public static void close() {
        if (!StarlinUtils.serverClosing) {
            StarlinUtils.serverClosing = true;
            FileConfiguration config = StarlinUtils.config.getConfig();
            Messager.broadcastTitle("&b&l服务器" + config.getInt("server-close-countdown") + "秒后重启", "&7请保管好个人物品!");
            new BukkitRunnable() {
                @Override
                public void run() {
                    StarlinUtils.INSTANCE.getServer().shutdown();
                }
            }.runTaskLater(StarlinUtils.INSTANCE, config.getInt("server-close-countdown") * 20);
        }
    }
}
