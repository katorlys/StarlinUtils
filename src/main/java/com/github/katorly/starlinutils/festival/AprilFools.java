package com.github.katorly.starlinutils.festival;

import java.text.SimpleDateFormat;
import java.util.Objects;

import com.github.katorly.starlinutils.backup.Messager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class AprilFools implements Listener {
    public boolean isToday(String date) {
        long t = System.currentTimeMillis();
        SimpleDateFormat d = new SimpleDateFormat("MM-dd");
        String n = d.format(t);
        if (Objects.equals(n, date)) return true;
        else return false;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent e) {
        if (isToday("04-01") && !e.getPlayer().isOp()) {
            Messager.sendMessage(e.getPlayer(), "&7&o[Server: 你被选中作为地球ol测试者, 将于今晚23:00入睡后进入游戏");
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (isToday("04-01")) {
            e.setDeathMessage(e.getEntity().getName() + "被海林干死了");
        }
    }

    @EventHandler
    public void onPlayerEat(PlayerToggleSneakEvent e) {
        if (isToday("04-01")) {
            e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 1));
        }
    }

    @EventHandler
    public void onPlayerTP(PlayerTeleportEvent e) {
        if (isToday("04-01")) {
            Messager.sendTitle(e.getPlayer(), "&c&l芜湖, 起飞!", "&7你起飞了!");
        }
    }
}
