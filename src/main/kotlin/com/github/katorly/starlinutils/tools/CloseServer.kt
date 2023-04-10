/*
 * Copyright (c) 2020-2023 Katorly Lab (https://github.com/katorlys)
 *
 * This Source Code Form is licensed under CC BY-NC-ND 4.0
 * (Attribution-NonCommercial-NoDerivatives 4.0
 * International). To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-nd/4.0/.
 */

package com.github.katorly.starlinutils.tools

import com.github.katorly.starlinutils.ConfigHandler.conf
import com.github.katorly.starlinutils.StarlinUtils
import com.github.katorly.starlinutils.StarlinUtils.plugin
import com.github.katorly.starlinutils.utils.Messager.bt
import taboolib.common.platform.function.onlinePlayers
import taboolib.common.platform.function.submit

/**
 * 关闭服务器前, 若有玩家在线, 则提醒 + 倒计时关服.
 * 若无, 则直接关闭服务器.
 *
 */
object CloseServer {
    fun close() {
        if (onlinePlayers().isNotEmpty()) {
            StarlinUtils.serverClosing = true
            bt(
                "&b&l服务器 ${conf.getInt("close-countdown")} 秒后重启",
                "&7请保管好个人物品!"
            )
            submit(delay = (conf.getInt("close-countdown").times(20)).toLong()) {
                plugin.server.shutdown()
            }
        } else {
            plugin.server.shutdown()
        }
    }
}