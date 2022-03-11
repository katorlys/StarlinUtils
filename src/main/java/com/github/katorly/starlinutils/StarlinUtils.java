package com.github.katorly.starlinutils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.github.katorly.starlinutils.backup.ConfigReader;
import com.github.katorly.starlinutils.commands.*;
import com.github.katorly.starlinutils.festival.AprilFools;
import com.github.katorly.starlinutils.utils.PlayTime;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class StarlinUtils extends JavaPlugin {
    public static StarlinUtils INSTANCE;
    public StarlinUtils() {
        INSTANCE = this;
    }

    public static ConfigReader config;
    public static ConfigReader timedata;
    public static ConfigReader monthly;
    public Map<UUID, Long> StartTime = new HashMap<>();
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(),this);
        getServer().getPluginManager().registerEvents(new AprilFools(),this);
        config = new ConfigReader(this,"","config.yml");
	    config.saveDefaultConfig();
        timedata = new ConfigReader(this,"","timedata.yml");
        monthly = new ConfigReader(this,"","monthly.yml");
        Bukkit.getPluginCommand("su").setExecutor(new su());
        Bukkit.getPluginCommand("su").setTabCompleter(new su());
        Bukkit.getPluginCommand("help").setExecutor(new help());
        Bukkit.getPluginCommand("givefly").setExecutor(new givefly());
        Bukkit.getPluginCommand("givefly").setTabCompleter(new givefly());
        Bukkit.getPluginCommand("listfly").setExecutor(new listfly());
        Bukkit.getPluginCommand("delfly").setExecutor(new delfly());
        Bukkit.getPluginCommand("delfly").setTabCompleter(new delfly());
        Recipe.registerConcreteRecipe(); //Add colored concrete
        Bukkit.getLogger().info("[StarlinUtils] Repo: https://github.com/katorlys/StarlinUtils");
        Bukkit.getLogger().info("[StarlinUtils] StarlinUtils enabled! Made for StarlinWorld server only.");
        for (Player p : Bukkit.getOnlinePlayers()) {
            try {
                PlayTime.initialize(p);
            } catch (ParseException e) {
                Bukkit.getLogger().severe("[StarlinUtils] Error counting player's monthly play time.");
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
                Bukkit.getLogger().severe("[StarlinUtils] Error counting player's monthly play time.");
                e.printStackTrace();
            }
        }
        Bukkit.clearRecipes();
        HandlerList.unregisterAll(this);
        ConfigReader.save(config);
        ConfigReader.save(timedata);
        ConfigReader.save(monthly);
        Bukkit.getLogger().info("[StarlinUtils] StarlinUtils disabled!");
    }
}