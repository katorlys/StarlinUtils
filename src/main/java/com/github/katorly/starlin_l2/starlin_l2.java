package com.github.katorly.starlin_l2;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.github.katorly.starlin_l2.backup.ConfigReader;
import com.github.katorly.starlin_l2.utils.PlayTime;
import com.github.katorly.starlin_l2.commands.*;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class starlin_l2 extends JavaPlugin {
    public static starlin_l2 INSTANCE;
    public starlin_l2() {
        INSTANCE = this;
    }

    public static ConfigReader config;
    public static ConfigReader timedata;
    public static ConfigReader monthly;
    public Map<UUID, Long> StartTime = new HashMap<>();
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(),this);
        config = new ConfigReader(this,"","config.yml");
	    config.saveDefaultConfig();
        timedata = new ConfigReader(this,"","timedata.yml");
        monthly = new ConfigReader(this,"","monthly.yml");
        Bukkit.getPluginCommand("l2").setExecutor(new l2());
        Bukkit.getPluginCommand("l2").setTabCompleter(new l2());
        Bukkit.getPluginCommand("help").setExecutor(new help());
        Bukkit.getPluginCommand("givefly").setExecutor(new givefly());
        Bukkit.getPluginCommand("givefly").setTabCompleter(new givefly());
        Bukkit.getPluginCommand("listfly").setExecutor(new listfly());
        Bukkit.getPluginCommand("delfly").setExecutor(new delfly());
        Bukkit.getPluginCommand("delfly").setTabCompleter(new delfly());
        Bukkit.getLogger().info("[starlin_l2] Repo: https://github.com/katorlys/Starlin_L2");
        Bukkit.getLogger().info("[starlin_l2] Starlin_L2 enabled! Made for StarlinWorld server only.");
        for (Player p : Bukkit.getOnlinePlayers()) {
            try {
                PlayTime.initialize(p);
            } catch (ParseException e) {
                Bukkit.getLogger().severe("[starlin_l2] Error counting player's monthly play time.");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDisable() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            try {
                PlayTime.settle(p);
            } catch (ParseException e) {
                Bukkit.getLogger().severe("[starlin_l2] Error counting player's monthly play time.");
                e.printStackTrace();
            }
        }
        HandlerList.unregisterAll(this);
        ConfigReader.save(config);
        ConfigReader.save(timedata);
        ConfigReader.save(monthly);
        Bukkit.getLogger().info("[starlin_l2] Starlin_L2 disabled!");
    }
}