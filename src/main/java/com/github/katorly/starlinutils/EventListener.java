package com.github.katorly.starlinutils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.github.katorly.starlinutils.backup.ConfigReader;
import com.github.katorly.starlinutils.backup.Messager;
import com.github.katorly.starlinutils.utils.CloserServer;
import com.github.katorly.starlinutils.utils.PlayTime;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) throws ParseException {
        
        if (StarlinUtils.serverClosing) { //Check whether server is going to close.
            Messager.sendTitle(e.getPlayer(), "&b&l服务器即将重启", "&7请保管好个人物品!");
        }

        PlayTime.initialize(e.getPlayer()); //Get player's join time and check whether player has joined before.

        long t = System.currentTimeMillis();
        FileConfiguration monthly = StarlinUtils.monthly.getConfig(); //Record monthly players.
        String pname = e.getPlayer().getName();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM");
        String timenow = dateFormat.format(t);
        if (!StarlinUtils.monthly.getConfig().contains(timenow)) {
            List<String> plist = new ArrayList<String>();
            plist.add(pname);
            monthly.set(timenow, plist);
            ConfigReader.save(StarlinUtils.monthly);
        } else {
            List<String> plist = monthly.getStringList(timenow);
            if (!plist.contains(pname)) {
                plist.add(pname);
                monthly.set(timenow, plist);
                ConfigReader.save(StarlinUtils.monthly);
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
                    Messager.sendMessage(p, "&b&l星林宇宙 &r&8>> &7检测到您在下界门处登录, 为防止您无法正常登录, 已将您传送到出生点!");
                    String x0 = String.format("%.2f", x); String y0 = String.format("%.2f", y); String z0 = String.format("%.2f", z);
                    Messager.sendMessage(p, "&b&l星林宇宙 &r&8>> &7下界门位置: " + x0 + ", " + y0 + ", " + z0);
                }
            }.runTaskLater(StarlinUtils.INSTANCE, 4L);
        }
    }

    @EventHandler //Count player's monthly play time.
    public void onPlayerLeave(PlayerQuitEvent e) throws ParseException {
        PlayTime.settle(e.getPlayer());
    }

    @EventHandler //Prevent crops from being trampled.
    public void onCropTrample(PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL && e.getClickedBlock().getType() == Material.FARMLAND) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent e) {
        if (Objects.equals(e.getMessage(), "/help") && !e.getPlayer().isOp()) { //Displays the help document when player excutes "/help".
            e.setCancelled(true);
            FileConfiguration config = StarlinUtils.config.getConfig();
            Messager.senderMessage(e.getPlayer(), "&b&l星林宇宙 &r&8>> &7新手指南: &f" + config.getString("help-document"));
        } else if (Objects.equals(e.getMessage(), "/stop") || Objects.equals(e.getMessage(), "/reload") || Objects.equals(e.getMessage(), "/restart")) {
            if (e.getPlayer().isOp()) {
                e.setCancelled(true);
                CloserServer.close();
            }
        }
    }

    @EventHandler
    public void onConsoleCommand(ServerCommandEvent e) {
        if (Objects.equals(e.getCommand(), "stop") || Objects.equals(e.getCommand(), "reload") || Objects.equals(e.getCommand(), "restart")) {
            e.setCancelled(true);
            CloserServer.close();
            FileConfiguration config = StarlinUtils.config.getConfig();
            Bukkit.getLogger().info("[StarlinUtils] 已执行重启命令. 还有" + config.getInt("server-close-countdown") + "重启.");
        }
    }
}