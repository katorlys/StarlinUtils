package com.github.katorly.starlinutils.tools

import com.github.katorly.starlinutils.ConfigHandler
import com.github.katorly.starlinutils.StarlinUtils
import com.github.katorly.starlinutils.utils.Messager
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.scheduler.BukkitRunnable

/**
 * 关闭服务器前, 若有玩家在线, 则提醒 + 倒计时关服.
 * 若无, 则直接关闭服务器.
 *
 */
class CloseServer {
    companion object {
        fun close() {
            if (Bukkit.getOnlinePlayers().isNotEmpty()) {
                if (!StarlinUtils.serverClosing) {
                    StarlinUtils.serverClosing = true
                    val config: FileConfiguration? = ConfigHandler.config.getConfig()
                    Messager.bt(
                        "&b&l服务器" + config?.getInt("server-close-countdown") + "秒后重启",
                        "&7请保管好个人物品!"
                    )
                    object : BukkitRunnable() {
                        override fun run() {
                            cancel()
                        }
                    }.runTaskLater(StarlinUtils.INSTANCE, (config?.getInt("server-close-countdown")?.times(20))!!.toLong())
                }
            }
            StarlinUtils.INSTANCE.server.shutdown()
        }
    }

}