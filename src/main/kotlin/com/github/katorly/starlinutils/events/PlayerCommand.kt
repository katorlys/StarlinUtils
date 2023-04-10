/*
 * Copyright (c) 2020-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.events

import com.github.katorly.starlinutils.ConfigHandler.conf
import com.github.katorly.starlinutils.ConfigHandler.prefix
import com.github.katorly.starlinutils.StarlinUtils
import com.github.katorly.starlinutils.tools.CloseServer
import com.github.katorly.starlinutils.utils.Messager.sm
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import taboolib.common.platform.event.EventPriority
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.adaptCommandSender
import taboolib.module.chat.ComponentText
import taboolib.module.chat.Components
import taboolib.module.chat.colored


object PlayerCommand {
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    fun onPlayerCommand(e: PlayerCommandPreprocessEvent) {
        /**
         * 若玩家执行 "/help", 推送帮助文档链接.
         *
         */
        if (e.message == "/help" && !e.player.isOp) {
            e.isCancelled = true
            val link: String = conf.getString("help-document") ?: "未知"
            val text: ComponentText = Components.text(" ")
            text.append("${prefix}新手指南: ".colored())
                .append("&f${link}".colored()).hoverText("&f点击打开!".colored()).clickOpenURL(link)
                .sendTo(adaptCommandSender(e.player))
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