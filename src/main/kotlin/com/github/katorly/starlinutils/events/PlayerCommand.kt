package com.github.katorly.starlinutils.events

import com.github.katorly.starlinutils.ConfigHandler.conf
import com.github.katorly.starlinutils.ConfigHandler.prefix
import com.github.katorly.starlinutils.StarlinUtils
import com.github.katorly.starlinutils.tools.CloseServer
import com.github.katorly.starlinutils.utils.Messager.sm
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import taboolib.common.platform.event.SubscribeEvent

object PlayerCommand {
    @SubscribeEvent
    fun onPlayerCommand(e: PlayerCommandPreprocessEvent) {
        /**
         * 若玩家执行 "/help", 推送帮助文档链接.
         *
         */
        if (e.message == "/help" && !e.player.isOp) {
            e.isCancelled = true
            val link: String? = conf["help-document"]
            sm(e.player, "${prefix}新手指南: &f${link}")
        }
        /**
         * 关闭服务器前, 若有玩家在线, 则提醒 + 倒计时关服.
         * 若无, 则直接关闭服务器.
         *
         */
        else if (e.message == "/stop" || e.message == "/reload" || e.message == "/restart") {
            if (e.player.isOp) {
                e.isCancelled = true
                if (StarlinUtils.serverClosing) sm(e.player, "${prefix}服务器已在重启倒计时中!")
                else CloseServer.close()
            }
        }
    }
}