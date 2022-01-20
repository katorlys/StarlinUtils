package com.github.katorly.starlin_l2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.github.katorly.starlin_l2.backup.configReader;
import com.github.katorly.starlin_l2.utils.MonthlyPlayTime;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class starlin_l2 extends JavaPlugin {
    public static starlin_l2 INSTANCE;
    public starlin_l2() {
        INSTANCE = this;
    }

    public static configReader timedata;
    public static configReader monthly;
    public Map<UUID, Long> StartTime = new HashMap<>();
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(),this);
        timedata = new configReader(this,"","timedata.yml");
	    timedata.saveDefaultConfig();
        monthly = new configReader(this,"","monthly.yml");
        Bukkit.getPluginCommand("l2").setExecutor(new CommandHandler());
        Bukkit.getPluginCommand("l2").setTabCompleter(new CommandHandler());
        Bukkit.getLogger().info("[starlin_l2] Repo: https://github.com/katorlys/Starlin_L2");
        Bukkit.getLogger().info("[starlin_l2] Starlin_L2 enabled! Made for StarlinWorld server only.");
        for (Player p : Bukkit.getOnlinePlayers()) {
            try {
                MonthlyPlayTime.initialize(p);
            } catch (ParseException e) {
                Bukkit.getLogger().severe("[starlin_l2] Error counting player's monthly play time.");
                e.printStackTrace();
            }
        }
        this.timeCounter();
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            try {
                MonthlyPlayTime.settle(p);
            } catch (ParseException e) {
                Bukkit.getLogger().severe("[starlin_l2] Error counting player's monthly play time.");
                e.printStackTrace();
            }
        }
        HandlerList.unregisterAll(this);
        configReader.save(timedata);
        Bukkit.getLogger().info("[starlin_l2] Starlin_L2 disabled!");
    }

    public void timeCounter() { //Counts the player's play time.
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    FileConfiguration timedata = starlin_l2.timedata.getConfig();
                    String u = player.getUniqueId().toString();
                    if (!starlin_l2.timedata.getConfig().contains(u)) { //if data not exist
                        timedata.set(u + ".name", player.getName());
                        long t = System.currentTimeMillis();
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
                        String timenow = dateFormat.format(t);
                        timedata.set(u + ".first-time", timenow);
                        timedata.set(u + ".total", 0.0);
                        configReader.save(starlin_l2.timedata);
                    } else {
                        Double newtotal = Double.valueOf(String.format("%.1f", timedata.getDouble(u + ".total"))) + 0.1; //if data exist
                        timedata.set(u + ".total", Double.valueOf(String.format("%.1f", newtotal)));
                        configReader.save(starlin_l2.timedata);
                    }
                }
            }
        }.runTaskTimer(this, 7200L, 7200L);
    }
}