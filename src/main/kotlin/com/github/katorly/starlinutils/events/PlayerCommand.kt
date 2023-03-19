package com.github.katorly.starlinutils.events

import com.github.katorly.starlinutils.ConfigHandler
import com.github.katorly.starlinutils.tools.CloseServer
import com.github.katorly.starlinutils.utils.Messager
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent

class PlayerCommand : Listener {
    @EventHandler
    fun onPlayerCommand(e: PlayerCommandPreprocessEvent) {
        /**
         * 若玩家执行 "/help", 推送帮助文档链接.
         *
         */
        if (e.message == "/help" && !e.player.isOp) {
            e.isCancelled = true
            val config: FileConfiguration? = ConfigHandler.config.getConfig()
            val link: String? = config?.getString("help-document")
            Messager.srm(e.player, "&b&l星林宇宙 &r&8>> &7新手指南: &f${link}")
        }
        /**
         * 关闭服务器前, 若有玩家在线, 则提醒 + 倒计时关服.
         * 若无, 则直接关闭服务器.
         *
         */
        else if (e.message == "/stop" || e.message == "/reload" || e.message == "/restart") {
            if (e.player.isOp) {
                e.isCancelled = true
                CloseServer.close()
            }
        }
    }
}