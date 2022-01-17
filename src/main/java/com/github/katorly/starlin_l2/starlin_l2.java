package com.github.katorly.starlin_l2;

import java.text.SimpleDateFormat;

import com.github.katorly.starlin_l2.backup.configReader;

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
    
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EventListener(),this);
        timedata = new configReader(this,"","timedata.yml");
	    timedata.saveDefaultConfig();
        monthly = new configReader(this,"","monthly.yml");
	    monthly.saveDefaultConfig();
        Bukkit.getLogger().info("[starlin_l2] Repo: https://github.com/katorlys/Starlin_L2");
        Bukkit.getLogger().info("[starlin_l2] Starlin_L2 enabled! Made for StarlinWorld server only.");
        this.timeCounter();
    }

    @Override
    public void onDisable() {
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
                        Double newtotal = Double.valueOf(String.format("%.2f", timedata.getDouble(u + ".total"))) + 0.1; //if data exist
                        timedata.set(u + ".total", newtotal);
                        configReader.save(starlin_l2.timedata);
                        //
                        //  Failed to achieve the following function: Count the player's play time every month.
                        //
                        //
                        //long t = System.currentTimeMillis();
                        //SimpleDateFormat d = new SimpleDateFormat("yyyy");
                        //String y = d.format(t);
                        //if (!starlin_l2.timedata.getConfig().contains(u + "." + y)) { //if data not exist
                        //    String ytime = "0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0";
                        //    String[] mtime = ytime.split(",");
                        //    SimpleDateFormat n = new SimpleDateFormat("M");
                        //    String m = n.format(t);
                        //    int month = Integer.valueOf(m);
                        //    mtime[month - 1] = String.valueOf(Double.valueOf(mtime[month - 1]) + 0.1);
                        //    String newtime = String.join(",", mtime);
                        //    timedata.set(u + "." + y, newtime);
                        //    configReader.save(starlin_l2.timedata);
                        //} else {
                        //    String ytime = timedata.getString(u + "." + y); //if data exist
                        //    String[] mtime = ytime.split(",");
                        //    SimpleDateFormat n = new SimpleDateFormat("M");
                        //    String m = n.format(t);
                        //    int month = Integer.valueOf(m);
                        //    mtime[month - 1] = String.valueOf(Double.valueOf(mtime[month - 1]) + 0.1);
                        //    String newtime = String.join(",", mtime);
                        //    timedata.set(u + "." + y, newtime);
                        //    configReader.save(starlin_l2.timedata);
                        //}
                    }
                }
            }
        }.runTaskTimer(this, 7200L, 7200L);
    }
}