package com.github.katorly.starlin_l2;

import org.bukkit.Bukkit;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class starlin_l2 extends JavaPlugin {
    public static starlin_l2 INSTANCE;

    public starlin_l2() {
        INSTANCE = this;
    }
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(),this);
        Bukkit.getLogger().info("[Starlin_L2] Author: Katorly");
        Bukkit.getLogger().info("[Starlin_L2] Starlin_L2 enabled! Made for StarlinWorld server only.");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        Bukkit.getLogger().info("[Starlin_L2] Starlin_L2 disabled!");
    }
}
