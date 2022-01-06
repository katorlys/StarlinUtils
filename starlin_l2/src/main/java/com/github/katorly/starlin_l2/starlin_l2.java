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
        Bukkit.getLogger().fine("Starlin_L2 enabled! Made for StarlinWorld server only.");
        Bukkit.getLogger().info("Author: Katorly");
    }

    @Override
    public void onDisable() {
        HandlerList.unregisterAll(this);
        Bukkit.getLogger().fine("Starlin_L2 disabled!");
    }
}
