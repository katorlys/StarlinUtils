package com.github.katorly.starlin_l2;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.github.katorly.starlin_l2.backup.configReader;
import com.github.katorly.starlin_l2.utils.PlayTime;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

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
        configReader.save(timedata);
        Bukkit.getLogger().info("[starlin_l2] Starlin_L2 disabled!");
    }
}