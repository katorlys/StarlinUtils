package com.github.katorly.starlinutils;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.github.katorly.starlinutils.backup.ConfigReader;
import com.github.katorly.starlinutils.commands.*;
import com.github.katorly.starlinutils.festival.AprilFools;
import com.github.katorly.starlinutils.utils.PlayTime;
import com.github.katorly.starlinutils.utils.SQLManager;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class StarlinUtils extends JavaPlugin {
    public static StarlinUtils INSTANCE;
    public StarlinUtils() {
        INSTANCE = this;
    }

    private Connection connection;
    public static ConfigReader config;
    public static ConfigReader timedata;
    public static ConfigReader monthly;
    public Map<UUID, Long> StartTime = new HashMap<>();
    public static boolean serverClosing = false;
    public final List<NamespacedKey> recipeKeys = new ArrayList<>();
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(),this);
        getServer().getPluginManager().registerEvents(new AprilFools(),this);
        connection = SQLManager.getNewConnection(); //Connect to database
        config = new ConfigReader(this,"","config.yml");
	    config.saveDefaultConfig();
        timedata = new ConfigReader(this,"","timedata.yml");
        monthly = new ConfigReader(this,"","monthly.yml");
        Bukkit.getPluginCommand("su").setExecutor(new su());
        Bukkit.getPluginCommand("su").setTabCompleter(new su());
        Bukkit.getPluginCommand("givefly").setExecutor(new givefly());
        Bukkit.getPluginCommand("givefly").setTabCompleter(new givefly());
        Bukkit.getPluginCommand("listfly").setExecutor(new listfly());
        Bukkit.getPluginCommand("delfly").setExecutor(new delfly());
        Bukkit.getPluginCommand("delfly").setTabCompleter(new delfly());
        Recipe.registerConcreteRecipe(); //Add colored concrete recipe
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
        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    if (connection != null && !connection.isClosed()) {
                        connection.createStatement().execute("SELECT 1");
                    }
                } catch (SQLException e) {
                    connection = SQLManager.getNewConnection();
                }
            }
        }.runTaskTimerAsynchronously(this, 1200, 1200);
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
        recipeKeys.forEach(Bukkit::removeRecipe);
        recipeKeys.clear();
        HandlerList.unregisterAll(this);
        ConfigReader.save(config);
        ConfigReader.save(timedata);
        ConfigReader.save(monthly);
        Bukkit.getLogger().info("[StarlinUtils] StarlinUtils disabled!");
    }
}