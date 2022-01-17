package com.github.katorly.starlin_l2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.github.katorly.starlin_l2.backup.configReader;
import com.github.katorly.starlin_l2.backup.messageSender;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EventListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {

        FileConfiguration timedata = starlin_l2.timedata.getConfig(); //Check whether player has joined before.
        String u = e.getPlayer().getUniqueId().toString();
        if (!starlin_l2.timedata.getConfig().contains(u)) { //if not
            timedata.set(u + ".name", e.getPlayer().getName());
            long t = System.currentTimeMillis();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            String timenow = dateFormat.format(t);
            timedata.set(u + ".first-time", timenow);
            timedata.set(u + ".total", 0.0);
            configReader.save(starlin_l2.timedata);
        }

        FileConfiguration monthly = starlin_l2.monthly.getConfig(); //Record monthly players.
        String pname = e.getPlayer().getName();
        long t = System.currentTimeMillis();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM");
        String timenow = dateFormat.format(t);
        if (!starlin_l2.monthly.getConfig().contains(timenow)) {
            List<String> plist = new ArrayList<String>();
            plist.add(pname);
            monthly.set(timenow, plist);
            configReader.save(starlin_l2.monthly);
        } else {
            List<String> plist = monthly.getStringList(timenow);
            if (!plist.contains(pname)) {
                plist.add(pname);
                monthly.set(timenow, plist);
                configReader.save(starlin_l2.monthly);
            }
        }

        final Player p = e.getPlayer(); //Prevent players from stucking in the Nether Portal when logging in.
        Location l = p.getLocation();
        final World w = l.getWorld();
        final double x = l.getX(); final double y= l.getY(); final double z = l.getZ();
        Location l_xl = new Location(w, x + 1, y, z);
        Location l_xr = new Location(w, x - 1, y, z);
        Location l_up = new Location(w, x, y + 1, z);
        Location l_down = new Location(w, x, y - 1, z);
        Location l_zl = new Location(w, x, y, z + 1);
        Location l_zr = new Location(w, x, y, z - 1);
        if ((l.getBlock().getType() == Material.NETHER_PORTAL)
        || (l_xl.getBlock().getType() == Material.NETHER_PORTAL) || (l_xr.getBlock().getType() == Material.NETHER_PORTAL)
        || (l_up.getBlock().getType() == Material.NETHER_PORTAL) || (l_down.getBlock().getType() == Material.NETHER_PORTAL)
        || (l_zl.getBlock().getType() == Material.NETHER_PORTAL) || (l_zr.getBlock().getType() == Material.NETHER_PORTAL)) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    Location spawn = w.getSpawnLocation();
                    p.teleport(spawn);
                    messageSender.sendMessage(p, "&b&l星林宇宙 &r&8>> &7检测到您在下界门处登录, 为防止您无法正常登录, 已将您传送到出生点!");
                    String x0 = String.format("%.2f", x); String y0 = String.format("%.2f", y); String z0 = String.format("%.2f", z);
                    messageSender.sendMessage(p, "&b&l星林宇宙 &r&8>> &7下界门位置: " + x0 + ", " + y0 + ", " + z0);
                }
            }.runTaskLater(starlin_l2.INSTANCE, 4L);
        }
    }

    @EventHandler //Prevent crops from being trampled.
    public void onCropTrample(PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.FARMLAND) {
            e.setCancelled(true);
        }
    }
}