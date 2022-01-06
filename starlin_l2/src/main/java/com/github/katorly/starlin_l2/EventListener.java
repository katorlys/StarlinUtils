package com.github.katorly.starlin_l2;

import com.github.katorly.starlin_l2.backup.MessageSender;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {
    @EventHandler
    public void onCropTrample(PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.FARMLAND) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Location l = p.getLocation();
        World w = l.getWorld();
        double x = l.getX(); double y= l.getY(); double z = l.getZ();
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
            p.chat("/spawn");
            MessageSender.sendMessage(p, "&b&l星林宇宙 &r&8>> &7检测到您在下界门处登录, 为防止您无法正常登录, 已将您传送到主城!");
        }
    }
}
