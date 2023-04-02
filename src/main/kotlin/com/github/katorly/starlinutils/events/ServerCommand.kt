/*
 * Copyright (c) 2020-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.events

import com.github.katorly.starlinutils.ConfigHandler.config
import com.github.katorly.starlinutils.ConfigHandler.prefix
import com.github.katorly.starlinutils.StarlinUtils
import com.github.katorly.starlinutils.tools.CloseServer
import org.bukkit.event.server.ServerCommandEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.info

object ServerCommand {
    @SubscribeEvent
    fun onServerCommand(e: ServerCommandEvent) {
        /**
         * 关闭服务器前, 若有玩家在线, 则提醒 + 倒计时关服.
         * 若无, 则直接关闭服务器.
         *
         */
        if (e.command == "stop" || e.command == "reload" || e.command == "restart") {
            e.isCancelled = true
            if (StarlinUtils.serverClosing) {
                info("${prefix}服务器已在重启倒计时中!")
            } else {
                CloseServer.close()
                info("[StarlinUtils] 已执行重启命令. 还有 ${config.getInt("close-countdown")} 秒重启.")
            }
        }
    }
}