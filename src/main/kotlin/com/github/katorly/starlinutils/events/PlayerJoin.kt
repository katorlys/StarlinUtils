package com.github.katorly.starlinutils.events

import com.github.katorly.starlinutils.StarlinUtils
import com.github.katorly.starlinutils.utils.Messager
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.scheduler.BukkitRunnable


class PlayerJoin : Listener {
    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        /**
         * 检测服务器是否即将关闭, 若是, 则提醒玩家.
         *
         */
        if (StarlinUtils.serverClosing) {
            Messager.st(e.player, "&b&l服务器即将重启", "&7请保管好个人物品!")
        }
        /**
         * 防卡下界门: 若玩家登录地点为下界门,
         * 则传送其至出生点, 防止玩家无法使用登录功能.
         *
         * 正版验证为否时打开.
         *
         */
        if (!Bukkit.getOnlineMode()) {
            val p: Player = e.player
            val l: Location = p.location
            val w: World = l.world!!
            val x: Double = l.x
            val y: Double = l.y
            val z: Double = l.z
            val l_xl = Location(w, x + 1, y, z)
            val l_xr = Location(w, x - 1, y, z)
            val l_up = Location(w, x, y + 1, z)
            val l_down = Location(w, x, y - 1, z)
            val l_zl = Location(w, x, y, z + 1)
            val l_zr = Location(w, x, y, z - 1)
            if (l.block.type == Material.NETHER_PORTAL || l_xl.block
                    .type == Material.NETHER_PORTAL || l_xr.block
                    .type == Material.NETHER_PORTAL || l_up.block
                    .type == Material.NETHER_PORTAL || l_down.block
                    .type == Material.NETHER_PORTAL || l_zl.block
                    .type == Material.NETHER_PORTAL || l_zr.block.type == Material.NETHER_PORTAL
            ) {
                object : BukkitRunnable() {
                    override fun run() {
                        val spawn: Location = w.spawnLocation
                        p.teleport(spawn)
                        Messager.sm(
                            p,
                            "&b&l星林宇宙 &r&8>> &7检测到您在下界门处上线, 为防止您无法正常登录, 已将您传送到出生点!"
                        )
                        val x0 = String.format("%.2f", x)
                        val y0 = String.format("%.2f", y)
                        val z0 = String.format("%.2f", z)
                        Messager.sm(p, "&b&l星林宇宙 &r&8>> &7下界门位置: $x0, $y0, $z0")
                    }
                }.runTaskLater(StarlinUtils.INSTANCE, 4L)
            }
        }
    }
}